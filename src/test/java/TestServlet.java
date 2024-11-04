import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestServlet {
    // Mock the reponse and request objects
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @BeforeEach
    public void setUp() throws Exception{
        // Initialize Mockito
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testDoGet() throws IOException, ServletException {
        // Create a PrinterWriter that writes to memory
        StringWriter stringWriter=new StringWriter();
        PrintWriter printWriter=new PrintWriter(stringWriter);

        // When the servlet requests the writer, give it this  PrintWriter
        when(response.getWriter()).thenReturn(printWriter);
        // When the servlet requests the path, tell it /patients
        when(request.getServletPath()).thenReturn("/patients");

        // Instantiate a servlet
        MyApplet myServlet=new MyApplet();
        // Test the doGet method, passing in the mocked response and request objects as parameters
        myServlet.doGet(request,response);

        // What was written to the writer - was it what was expected?
        String output=stringWriter.getBuffer().toString();
        assertThat(output,is(equalTo("Hello world Tsuru attempts works")));
    }
}



