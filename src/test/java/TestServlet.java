import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestServlet {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testDoGet() throws IOException, ServletException {
        StringWriter stringWriter=new StringWriter();
        PrintWriter printWriter=new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);
        when(request.getServletPath()).thenReturn("/patients");

        MyApplet myServlet=new MyApplet();
        myServlet.doGet(request,response);

        String output=stringWriter.getBuffer().toString();
        assertThat(output,is(equalTo("Hello, world!")));
    }
}



