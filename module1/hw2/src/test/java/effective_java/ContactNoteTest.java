package effective_java;

import com.epam.cdp.effective.java.methods.common.ContactNote;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactNoteTest {

    @Test
    public void test(){
        ContactNote note = new ContactNote("First Name", "Last Name", null);
        note.setPhoneNumber("0993526424");
        ContactNote note1 = new ContactNote("First Name1", "Last Name2", "0953435423");

        System.out.println(note);

        assertEquals(1, note1.compareTo(note));

        assertEquals(-1, note.compareTo(note1));

        assertEquals(-1, note.compareTo(null));
    }
}