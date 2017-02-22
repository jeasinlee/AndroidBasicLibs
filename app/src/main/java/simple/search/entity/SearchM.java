package simple.search.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：LiuShenEn on 2017/2/21 15:37
 */
@Entity
public class SearchM {

    @Id(autoincrement = true)
    private Long id;
    private String keyName;

    @Generated(hash = 1894705078)
    public SearchM(Long id, String keyName) {
        this.id = id;
        this.keyName = keyName;
    }

    @Generated(hash = 351106736)
    public SearchM() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}
