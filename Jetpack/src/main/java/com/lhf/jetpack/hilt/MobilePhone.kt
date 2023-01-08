package com.lhf.jetpack.hilt

import javax.inject.Inject

/**
 * 普通带参数对象注入
 *
 * @author Joshua
 * @date 2023/1/8 14:23
 */
class MobilePhone @Inject constructor(val simCard: SimCard) {

    fun dialNumber() {
        simCard.dialNumber()
    }
}