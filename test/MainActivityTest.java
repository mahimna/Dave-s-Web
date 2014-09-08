
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Intent;
import android.widget.Button;

import com.example.davesweb.ListViewControl;
import com.example.davesweb.MainActivity;
import com.example.davesweb.R;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    MainActivity activity;

    @Before
    public void setup()
    {
        this.activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void shouldHaveHappySmiles() throws Exception 
    {
        String hello = this.activity.getString(R.string.hello_world);
        assertThat(hello, equalTo("Hello world!"));
    }
    
    @Test
    public void buttonClickShouldOpenSecondActivity() throws Exception 
    {
    	Button button = (Button) activity.findViewById(R.id.getBooks);
    	button.performClick();
    	Intent intent = Robolectric.shadowOf(activity).peekNextStartedActivity();
    	assertEquals(ListViewControl.class.getCanonicalName(),intent.getComponent().getClassName());
    }
}