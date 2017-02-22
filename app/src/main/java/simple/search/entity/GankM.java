package simple.search.entity;

/**
 * author：LiuShenEn on 2017/2/21 10:52
 */
public class GankM {
    /**
     * 标题
     */
    private String title;
    /**
     * 类型
     */
    private String type;
    /**
     * 来源
     */
    private String source;
    /**
     * 地址
     */
    private String url;

    public GankM(String title, String type, String source, String url) {
        this.title = title;
        this.type = type;
        this.source = source;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
