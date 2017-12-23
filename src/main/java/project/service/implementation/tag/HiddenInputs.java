package project.service.implementation.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by SCIP on 15.09.2016.
 */
public class HiddenInputs extends SimpleTagSupport {

    private final StringWriter sw = new StringWriter();
    private List<String> excludes = new ArrayList<>();

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if (!excludes.contains(entry.getKey())) {
                for (String value : entry.getValue()) {
                    sw.append("<input type='hidden' ");
                    sw.append("name='");
                    sw.append(entry.getKey());
                    sw.append("' value='");
                    sw.append(value);
                    sw.append("'>");
                }
            }
        }
        out.println(sw.toString());
    }

    public void setExcludeParams(String excludeParams) {
        StringTokenizer tokenizer = new StringTokenizer(excludeParams, ", ");
        while (tokenizer.hasMoreTokens()) {
            excludes.add(tokenizer.nextToken());
        }
    }
}
