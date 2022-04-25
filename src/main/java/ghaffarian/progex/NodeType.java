package ghaffarian.progex;

public enum NodeType {
    ROOT        ("ROOT"),
    HELP        ("HELP"),
    ISO         ("ISO"),
    RETURN      ("RETURN"),
    BREAK       ("BREAK"),
    THROW       ("THROW"),
    CONTINUE    ("CONTINUE"),
    TRY         ("TRY"),
    CATCH       ("CATCH"),
    SWITCH      ("SWITCH"),
    CASE        ("CASE"),
    DEFAULT     ("DEFAULT"),
    SYNCHRONIZED ("SYNCHRONIZED"),
    WHILE       ("WHILE"),
    FOR         ("FOR"),
    IF          ("IF"),
    NORMAL      ("NORMAL");
    public final String label;

    private NodeType(String lbl) {
        label = lbl;
    }

    @Override
    public String toString() {
        return label;
    }
}
