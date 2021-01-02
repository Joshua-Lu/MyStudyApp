package com.lhf.javacommonlib.media;

import com.lhf.javacommonlib.util.CommonUtils;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 * Created by Joshua on 2021/1/2 17:07
 */
public class SoundPlayer {
    public static void main(String[] args) {
        SoundPlayer soundPlayer = new SoundPlayer();
        while (true) {
            String instrument = CommonUtils.getUserInput("请输入乐器（范围[0,127]）：");
            String note = CommonUtils.getUserInput("请输入音符（范围[0,127]）：");
            soundPlayer.play(Integer.parseInt(instrument), Integer.parseInt(note));
        }
    }

    public void play(int instrument, int note) {
        try {
            // 1.获取Sequencer对象并将它打开
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            // 2.创建新的Sequence对象
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            // 3.从Sequence中创建新的Track
            Track track = sequence.createTrack();
            // 4.track中填入MidiEvent
            track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, instrument, 0), 1));
            track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 1, note, 100), 1));
            track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 1, note, 100), 16));
            // 5.将Sequence设给Sequencer，并开始播放
            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
