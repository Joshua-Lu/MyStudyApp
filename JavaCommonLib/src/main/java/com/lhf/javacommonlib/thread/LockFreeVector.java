package com.lhf.javacommonlib.thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 实现一个无锁的Vector （《java高并发》P171）
 * 核心：通过AtomicReferenceArray内部CAS操作实现
 *
 * @author Joshua
 * @date 2021/12/16 0:34
 */
public class LockFreeVector<E> {
    // 数组个数
    private static final int N_BUKET = 30;
    // 第一个数组的大小，后面依次乘2
    private static final int FIRST_BUKET_SIZE = 8;
    // 第一个数组的前导0个数
    int zeroNumFirst = Integer.numberOfLeadingZeros(FIRST_BUKET_SIZE);
    /**
     * 使用一个二维数组来存储数据
     */
    private AtomicReferenceArray<AtomicReferenceArray<E>> buckets;
    private AtomicReference<Descriptor<E>> descriptorReference;

    public LockFreeVector() {
        buckets = new AtomicReferenceArray<AtomicReferenceArray<E>>(N_BUKET);
        buckets.set(0, new AtomicReferenceArray<E>(FIRST_BUKET_SIZE));
        descriptorReference = new AtomicReference<>(new Descriptor<>(0, null));
    }

    public void pushBack(E e) {
        Descriptor<E> descriptor;
        Descriptor<E> newDescriptor;
        do {
            descriptor = descriptorReference.get();
            descriptor.completeWrite();

            // TODO:@lhf pushBack: FIRST_BUKET_SIZE是导致size进位的最小条件 P174
            int pos = descriptor.size + FIRST_BUKET_SIZE;
            int zeroNumPos = Integer.numberOfLeadingZeros(pos);
            // 元素所在的数组
            int bucketIndex = zeroNumFirst - zeroNumPos;
            // 对应数组为null，则需要扩容
            if (buckets.get(bucketIndex) == null) {
                int newLen = 2 * buckets.get(bucketIndex - 1).length();
                buckets.compareAndSet(bucketIndex, null, new AtomicReferenceArray<E>(newLen));
            }
            // 元素在所在数组中的位置
            int idx = (0x80000000 >>> zeroNumPos) ^ pos;
            newDescriptor = new Descriptor<>(descriptor.size + 1, new WriteDescriptor<>(buckets.get(bucketIndex),
                    idx, null, e));
        } while (!descriptorReference.compareAndSet(descriptor, newDescriptor));
        descriptorReference.get().completeWrite();
    }

    public E get(int index) {
        int pos = index + FIRST_BUKET_SIZE;
        int zeroNumPos = Integer.numberOfLeadingZeros(pos);
        int bucketIndex = zeroNumFirst - zeroNumPos;
        int idx = (0x80000000 >>> zeroNumPos) ^ pos;
        return buckets.get(bucketIndex).get(idx);
    }


    static class Descriptor<E> {
        public int size;
        volatile WriteDescriptor<E> writeDescriptor;

        public Descriptor(int size, WriteDescriptor<E> writeDescriptor) {
            this.size = size;
            this.writeDescriptor = writeDescriptor;
        }

        public void completeWrite() {
            WriteDescriptor<E> temp = writeDescriptor;
            if (temp != null) {
                temp.doIt();
                writeDescriptor = null;
            }
        }
    }

    static class WriteDescriptor<E> {
        public AtomicReferenceArray<E> addr;
        public int addr_index;
        public E oldV;
        public E newV;

        public WriteDescriptor(AtomicReferenceArray<E> addr, int addr_index, E oldV, E newV) {
            this.addr = addr;
            this.addr_index = addr_index;
            this.oldV = oldV;
            this.newV = newV;
        }

        public void doIt() {
            addr.compareAndSet(addr_index, oldV, newV);
        }

    }
}
