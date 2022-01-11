package ProductShop.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Photo implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name= "uuid",strategy = "uuid2")
    private String id;
    
    
    private String name;
    private String mime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    public Photo() {
    }

    public Photo(String name, String mime, byte[] content) {
      
        this.name = name;
        this.mime = mime;
        this.content = content;
    }

    /**
     * @return the nombre
     */
    public String getName() {
        return name;
    }

    /**
     * @return the mime
     */
    public String getMime() {
        return mime;
    }

    /**
     * @return the contenido
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param mime the mime to set
     */
    public void setMime(String mime) {
        this.mime = mime;
    }

    /**
     * @param content
    
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

}
