package by.epamtc.lyskovkirill.restaurant.util.taglib;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.List;

/**
 * Custom jsp tag class that is responsible for viewing the {@link Dish} list.
 *
 * @author k1ly
 */
public class MenuViewerTag extends TagSupport {
    private List<Dish> dishList;

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if (dishList != null) {
                JspWriter out = pageContext.getOut();
                if (dishList.size() > 0) {
                    out.println("<table>");
                    for (Dish dish : dishList) {
                        out.println("<tr><td class='menu-dish-td'>");
                        out.println("<div class='menu-dish' data-id='" + dish.getId() + "'>");
                        out.println("<div style='padding: 10px; width:160px; height: 160px'>");
                        out.println("<img class='dish-image' src='" + dish.getImage() + "'/>");
                        out.println("</div>");
                        out.println("<div style='padding: 10px;'>" + dish.getName() + "</div>");
                        out.println("<div style='padding: 10px;'>" + dish.getComposition() + "</div>");
                        out.println("<div style='padding: 10px;'>" + dish.getWeight() + "g.</div>");
                        out.println("<div style='padding: 10px;'>" + dish.getPrice() + "&dollar;</div>");
                        if (dish.getDiscount() != null && dish.getDiscount() > 0)
                            out.println("<div style='padding: 10px;'>" + dish.getDiscount() + "%</div>");
                        out.println("<input style='float: right' class='menu-add-dish' type='button' value='Добавить в корзину'/>");
                        out.println("</div>");
                        out.println("</td></tr>");
                    }
                    out.println("</table>");
                }
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}