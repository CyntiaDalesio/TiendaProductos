package egg.prueba.demo.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Photo {

    @Id
    private String id;
    private String nombre;
    private String mime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
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
    public byte[] getContenido() {
        return contenido;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param mime the mime to set
     */
    public void setMime(String mime) {
        this.mime = mime;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

}
