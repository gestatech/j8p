package io.r2.j8p.t4_nio2;

import java.io.FileInputStream;
import java.net.Socket;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.*;
import java.util.Set;

/**
 * Quick review of basic java.nio operation
 */
public class NioCore {

    public void buffers() {
        ByteBuffer b1 = ByteBuffer.allocate(512);
        // used for direct I/O operation, possibly faster, higher maintenance
        ByteBuffer b2 = ByteBuffer.allocateDirect(512);
        //
        byte[] buf = new byte[512];
        ByteBuffer b3 = ByteBuffer.wrap(buf);

        byte b = b1.get();

        byte[] outbuf = new byte[512];
        b1.get(outbuf);

        // bytebuffer can be converted
        LongBuffer l = b1.asLongBuffer();

        // and read as different types directly
        b1.getFloat();
        b1.putInt(0x123).putFloat(0.7f);

        // can use with index
        b1.get(32);

        // positioning
        b1.rewind();
        b1.mark();
        b1.reset(); // return to mark

        // remaining / size / pos
        b1.hasRemaining();
        b1.remaining();
        b1.limit();
        b1.position();

        IntBuffer i1 = IntBuffer.allocate(128);
        int i = i1.get(); // read an int

        // don't forget byteorder
        ByteOrder o = i1.order();
        b1.order(ByteOrder.LITTLE_ENDIAN);
        b2.order(ByteOrder.nativeOrder());
    }

    public void channels() throws Exception {
        // for scokets?
        Socket s = null;
        SocketChannel sc = s.getChannel();

        // chanelles support read/write to/from buffers
        ByteBuffer b = ByteBuffer.allocate(123);
        sc.read(b);
        sc.write(b);
        // scatter/gather
        ByteBuffer b2 = ByteBuffer.allocate(123);
        sc.read(new ByteBuffer[] { b, b2 });
        sc.write(new ByteBuffer[] { b, b2 });

        // for file a special seekable verion
        FileInputStream in = null;
        FileChannel fc = in.getChannel();

        fc.read(b);
        fc.write(b2);
        fc.position(552);
        fc.size();

        // memory mapping (real fun)
        MappedByteBuffer mb = fc.map(FileChannel.MapMode.READ_ONLY, 0, 412);
        // other mapping modes: READ_WRITE, PRIVATE (copy on write)
        mb.getFloat(); // this is actually a buffer
        mb.position(512);

        // closeable
        fc.close();

        // even autocloseable
        try (FileChannel fc2 = in.getChannel()) {

        }
    }

    public void selector() throws Exception {
        // non-blocking operation using selector
        SocketChannel c1 = null;
        SocketChannel c2 = null;

        // create selector
        Selector sel = Selector.open();

        // set channels to non-blocking
        c1.configureBlocking(false);
        c2.configureBlocking(false);

        // register channels to selector
        SelectionKey sk1 = c1.register(sel, SelectionKey.OP_READ);
        SelectionKey sk2 = c2.register(sel, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        // other operations: OP_ACCEPT, OP_CONNECT

        // selection key will report things
        sk2.isReadable();
        sk1.isWritable();
        sk1.selector();
        sk1.channel();

        // extra object attachment - for anything
        sk1.attach(new Object());
        sk1.attachment();

        // now go on and select
        sel.select(); // wait until one of them is ready
        sel.select(5000); // wait 5 seconds to be ready
        sel.selectNow(); // do not wait just check

        // select* returns number of keys available
        Set<SelectionKey> keys = sel.selectedKeys();

        // extra
        sel.wakeup(); // interrupt any select (on a different thread)
    }

}
