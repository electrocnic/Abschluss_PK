import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class SlangTest {
    public boolean waiting = true;
    public class FakeInputStream extends InputStream {
        @Override
        public int read() throws IOException {
            return -1;
        }
    }

    public class MyThread extends Thread {
        public void run(){
            try {
                this.sleep(1000);
            } catch (Exception e){

            }
            waiting = false;
        }
    }
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    public void testMain() throws Exception {

        File curDir = new File(".");

        File[] filesList = curDir.listFiles();
        for(File f : filesList){
            String name = f.toString();
            String extension = name.substring(name.lastIndexOf(".") + 1, f.toString().length());
            if(f.isFile() && extension == "slang"){
                testFile(name);
            }
        }
    }

    public void testFile(String s){
        try {
            FileInputStream f = new FileInputStream("progs/" + s + ".slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/" + s + ".output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            assertEquals(expected, outContent.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test00(){
        try {
            FileInputStream f = new FileInputStream("progs/00-dot-s-string.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/00-dot-s-string.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r","");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test01(){
        try {
            FileInputStream f = new FileInputStream("progs/01-dot-s-integer.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/01-dot-s-integer.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test02(){
        try {
            FileInputStream f = new FileInputStream("progs/02-dot-s-mixed.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/02-dot-s-mixed.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test10(){
        try {
            FileInputStream f = new FileInputStream("progs/10-prin.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/10-prin.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test11(){
        try {
            FileInputStream f = new FileInputStream("progs/11-print.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/11-print.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test12(){
        try {
            FileInputStream f = new FileInputStream("progs/12-prin-print.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/12-prin-print.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test20(){
        try {
            FileInputStream f = new FileInputStream("progs/20-sub.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/20-sub.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test21(){
        try {
            FileInputStream f = new FileInputStream("progs/21-add.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/21-add.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test22(){
        try {
            FileInputStream f = new FileInputStream("progs/22-add-multi.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/22-add-multi.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test23(){
        try {
            FileInputStream f = new FileInputStream("progs/23-div.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/23-div.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test25(){
        try {
            FileInputStream f = new FileInputStream("progs/23-mul.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/23-mul.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test24(){
        try {
            FileInputStream f = new FileInputStream("progs/24-mod.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/24-mod.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test30(){
        try {
            FileInputStream f = new FileInputStream("progs/30-read-file.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/30-read-file.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test31(){
        try {
            FileInputStream f = new FileInputStream("progs/31-read-http.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/31-read-http.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    @Test
    public void test32(){
        try {
            FileInputStream f = new FileInputStream("progs/32-read-https.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/32-read-https.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test40(){
        try {
            FileInputStream f = new FileInputStream("progs/40-write.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/40-write.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/
    @Test
    public void test50(){
        try {
            FileInputStream f = new FileInputStream("progs/50-append.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/50-append.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test51(){
        try {
            FileInputStream f = new FileInputStream("progs/51-append-integer.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/51-append-integer.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test52(){
        try {
            FileInputStream f = new FileInputStream("progs/52-append-mixed.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/52-append-mixed.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test60(){
        try {
            FileInputStream f = new FileInputStream("progs/60-skip-to-write.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/60-skip-to.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test61(){
        try {
            FileInputStream f = new FileInputStream("progs/61-skip-to-not-found.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/61-skip-to-not-found.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test62(){
        try {
            FileInputStream f = new FileInputStream("progs/62-skip-to-multi.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/62-skip-to-multi.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test63(){
        try {
            FileInputStream f = new FileInputStream("progs/63-skip-n.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/63-skip-n.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test64(){
        try {
            FileInputStream f = new FileInputStream("progs/64-skip-n-0.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/64-skip-n-0.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test65(){
        try {
            FileInputStream f = new FileInputStream("progs/65-skip-n-100.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/65-skip-n-100.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test66(){
        try {
            FileInputStream f = new FileInputStream("progs/66-skip-n-empty.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/66-skip-n-empty.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test67(){
        try {
            FileInputStream f = new FileInputStream("progs/67-skip-n-short.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/67-skip-n-short.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test68(){
        try {
            FileInputStream f = new FileInputStream("progs/68-skip-mixed.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/68-skip-mixed.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test70(){
        try {
            FileInputStream f = new FileInputStream("progs/70-trunc.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/70-trunc.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test71(){
        try {
            FileInputStream f = new FileInputStream("progs/71-trunc-zero.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/71-trunc-zero.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test72(){
        try {
            FileInputStream f = new FileInputStream("progs/72-trunc-long.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/72-trunc-long.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test73(){
        try {
            FileInputStream f = new FileInputStream("progs/73-trunc-spaces.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/73-trunc-spaces.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test74(){
        try {
            FileInputStream f = new FileInputStream("progs/74-skip-trunc.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/74-skip-trunc.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test76(){
        try {
            FileInputStream f = new FileInputStream("progs/76-copy-to-not-found.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/76-copy-to-not-found.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test77(){
        try {
            FileInputStream f = new FileInputStream("progs/77-skip-trunc-copy.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/77-skip-trunc-copy.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test78(){
        try {
            FileInputStream f = new FileInputStream("progs/78-skip-integer.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/78-skip-integer.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test79(){
        try {
            FileInputStream f = new FileInputStream("progs/79-trunc-copy-integer.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/79-trunc-copy-integer.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test80(){
        try {
            FileInputStream f = new FileInputStream("progs/80-dup.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/80-dup.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test81(){
        try {
            FileInputStream f = new FileInputStream("progs/81-pop.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/81-pop.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test89(){
        try {
            FileInputStream f = new FileInputStream("progs/90-lexer-error.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/90-lexer-error.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test90(){
        try {
            FileInputStream f = new FileInputStream("progs/90-lexer-error-bracestring.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/90-lexer-error-bracestring.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test91(){
        try {
            FileInputStream f = new FileInputStream("progs/91-parser-error.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/91-parser-error.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test92(){
        try {
            FileInputStream f = new FileInputStream("progs/92-empty-print.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/92-empty-print.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test93(){
        try {
            FileInputStream f = new FileInputStream("progs/93-div-zero.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/93-div-zero.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test94(){
        try {
            FileInputStream f = new FileInputStream("progs/94-file-not-found.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/94-file-not-found.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test95(){
        try {
            FileInputStream f = new FileInputStream("progs/95-conversion.slang");
            System.setIn(f);
            Slang.main(new String[]{});
            FileReader fr = new FileReader("progs/95-conversion.output");
            StringBuffer expected = new StringBuffer();
            int c;
            while ((c = fr.read())!= -1){
                expected.append((char)c);
            }
            String s1 = expected.toString().replaceAll("\r","");
            String s2 = outContent.toString().replaceAll("\r", "");
            assertEquals(s1, s2 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}