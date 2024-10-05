package GUI.Component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PriceDocumentFilter extends NumericDocumentFilter{
    public PriceDocumentFilter(int limit){
        super(limit);
    }

     @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        char c = ' ';
        if (offset == 0){
            try{
                c = string.charAt(0);
            }
            catch (Exception ignore){
                
            }
        }

        if (c == '0') return;
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        char c = ' ';
        if (offset == 0){
            try{
                c = text.charAt(0);
            }
            catch (Exception ignore){
                
            }
        }

        if (c == '0') return;
        super.replace(fb, offset, length, text, attrs);
    }
    
}