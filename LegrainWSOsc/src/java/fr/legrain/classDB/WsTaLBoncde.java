/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.legrain.classDB;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lee
 */
@Entity
@Table(name="ws_l_boncde")
public class WsTaLBoncde implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id_l_boncde")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public WsTaLBoncde() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WsTaLBoncde)) {
            return false;
        }
        WsTaLBoncde other = (WsTaLBoncde) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.legrain.classDB.WsTaLBoncde[id=" + id + "]";
    }

}
