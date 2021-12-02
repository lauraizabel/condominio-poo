import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="teste", schema="public")
public class Teste {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name = "name")
    private String name;

    public Teste() {
        super();
    }

    public void setId(Integer id){
        this.id = id;
    }
    public void setName(String name) { this.name = name; }
}

   
