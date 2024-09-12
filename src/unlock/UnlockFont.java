package unlock;

import java.awt.Font;

public class UnlockFont {
    
    private static final Font TITLEFONT = new Font("Century Schoolbook", Font.BOLD, 25);
    private static final Font SUBTITLEFONT = new Font("Century Schoolbook", Font.ITALIC, 15);
    private static final Font BUTTONFONT = new Font("Century Schoolbook", Font.BOLD, 15);
    
    public UnlockFont() {}
    
    public static Font setFont(String fontName, int fontType, int size) {
        
        return new Font(fontName, fontType, size);
    }
    
    public static Font getTitleFont() {
        
        return TITLEFONT;
    }
    
    public static Font getSubtitleFont() {
        
        return SUBTITLEFONT;
    }
    
    public static Font getButtonFont() {
        
        return BUTTONFONT;
    }
}
