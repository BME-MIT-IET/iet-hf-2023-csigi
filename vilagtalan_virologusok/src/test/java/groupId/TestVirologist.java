package groupId;

import domain.Agens;
import domain.Hatas;
import domain.Mezo;
import domain.Virologus;

import java.util.ArrayList;
import java.util.List;

public class TestVirologist extends Virologus {

    public void addAgent(Agens a){
        this.agensek.add(a);
    }
    public void setField(Mezo f){
        this.mostMezo = f;
    }

    public void addAgentCode(Agens agent) {
        this.ismertKodok.add(agent);
    }

    public void setNeighbours(ArrayList<Mezo> neighbours){
        this.szomszedok = neighbours;
    }
}
