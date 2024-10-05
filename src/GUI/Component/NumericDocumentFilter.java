package GUI.Component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericDocumentFilter extends DocumentFilter {
    private int limit = 0;

    public NumericDocumentFilter(){
        super();
    }

    public NumericDocumentFilter(int limit){
        super();
        this.limit = limit;
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null || !string.matches("\\d*")) {
            return;
        }

        if (limit != 0 && offset + string.length() >= limit) {
            return;
        }

        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null || !text.matches("\\d*")) return;

        if (limit != 0 && offset + text.length() >= limit) {
            return;
        }

        super.replace(fb, offset, length, text, attrs);
    }
}


