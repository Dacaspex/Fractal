package org.jcodec.codecs.aac;
import static org.jcodec.codecs.aac.ObjectType.AOT_ESCAPE;

import org.jcodec.codecs.mpeg4.mp4.EsdsBox;
import org.jcodec.common.AudioFormat;
import org.jcodec.common.io.BitReader;
import org.jcodec.common.io.BitWriter;
import org.jcodec.common.model.ChannelLabel;
import org.jcodec.containers.mp4.boxes.Box.LeafBox;
import org.jcodec.containers.mp4.boxes.NodeBox;
import org.jcodec.containers.mp4.boxes.SampleEntry;

import java.lang.IllegalArgumentException;
import java.nio.ByteBuffer;

/**
 * This class is part of JCodec ( www.jcodec.org ) This software is distributed
 * under FreeBSD License
 * 
 * @author The JCodec project
 * 
 */
public class AACUtils {

    public static class AACMetadata {
        private AudioFormat format;
        private ChannelLabel[] labels;

        public AACMetadata(AudioFormat format, ChannelLabel[] labels) {
            this.format = format;
            this.labels = labels;
        }

        public AudioFormat getFormat() {
            return format;
        }

        public ChannelLabel[] getLabels() {
            return labels;
        }
    }

    private static int getObjectType(BitReader reader) {
        int objectType = reader.readNBit(5);
        if (objectType == AOT_ESCAPE.ordinal())
            objectType = 32 + reader.readNBit(6);
        return objectType;
    }

    private static ChannelLabel[][] AAC_DEFAULT_CONFIGS = {
            null, //
            { ChannelLabel.MONO }, //
            { ChannelLabel.STEREO_LEFT, ChannelLabel.STEREO_RIGHT }, //
            { ChannelLabel.CENTER, ChannelLabel.FRONT_LEFT, ChannelLabel.FRONT_RIGHT }, //
            { ChannelLabel.CENTER, ChannelLabel.FRONT_LEFT, ChannelLabel.FRONT_RIGHT, ChannelLabel.REAR_CENTER }, //
            { ChannelLabel.CENTER, ChannelLabel.FRONT_LEFT, ChannelLabel.FRONT_RIGHT, ChannelLabel.REAR_LEFT,
                    ChannelLabel.REAR_RIGHT }, //
            { ChannelLabel.CENTER, ChannelLabel.FRONT_LEFT, ChannelLabel.FRONT_RIGHT, ChannelLabel.REAR_LEFT,
                    ChannelLabel.REAR_RIGHT, ChannelLabel.LFE }, //
            { ChannelLabel.CENTER, ChannelLabel.FRONT_LEFT, ChannelLabel.FRONT_RIGHT, ChannelLabel.SIDE_LEFT,
                    ChannelLabel.SIDE_RIGHT, ChannelLabel.REAR_LEFT, ChannelLabel.REAR_RIGHT, ChannelLabel.LFE } //
    };

    @SuppressWarnings("unused")
	public static AACMetadata parseAudioInfo(ByteBuffer privData) {
        BitReader reader = BitReader.createBitReader(privData);

        int objectType = getObjectType(reader);
        int index = reader.readNBit(4);
        int sampleRate = index == 0x0f ? reader.readNBit(24) : AACConts.AAC_SAMPLE_RATES[index];
        int channelConfig = reader.readNBit(4);

        if (channelConfig == 0 || channelConfig >= AAC_DEFAULT_CONFIGS.length)
            return null;

        ChannelLabel[] channels = AAC_DEFAULT_CONFIGS[channelConfig];
        return new AACMetadata(new AudioFormat(sampleRate, 16, channels.length, true, false), channels);
    }

    public static AACMetadata getMetadata(SampleEntry mp4a) {
        if (!"mp4a".equals(mp4a.getFourcc()))
            throw new IllegalArgumentException("Not mp4a sample entry");
        ByteBuffer b = getCodecPrivate(mp4a);
        if (b == null)
            return null;

        return parseAudioInfo(b);
    }

    public static ByteBuffer getCodecPrivate(SampleEntry mp4a) {
        LeafBox b = NodeBox.findFirst(mp4a, LeafBox.class, "esds");
        if (b == null) {
            b = NodeBox.findFirstPath(mp4a, LeafBox.class, new String[] { null, "esds" });
        }
        if (b == null)
            return null;
        EsdsBox esds = EsdsBox.newEsdsBox();
        esds.parse(b.getData());
        return esds.getStreamInfo();
    }
    
    public static ByteBuffer adtsToStreamInfo(org.jcodec.codecs.aac.ADTSParser.Header hdr) {
        ByteBuffer si = ByteBuffer.allocate(2);
        BitWriter wr = new BitWriter(si);
        wr.writeNBit(hdr.getObjectType(), 5);
        wr.writeNBit(hdr.getSamplingIndex(), 4);
        wr.writeNBit(hdr.getChanConfig(), 4);
        wr.flush();
        si.clear();
        return si;
    }
}