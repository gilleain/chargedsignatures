package chargedsigs;

import java.util.List;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;

import signature.AbstractVertexSignature;

public class ChargedAtomSignature extends AbstractVertexSignature {
    
    private IAtomContainer atomContainer;
    
    public ChargedAtomSignature(int atomIndex, IAtomContainer atomContainer) {
        super();
        this.atomContainer = atomContainer;
        super.createMaximumHeight(atomIndex, atomContainer.getAtomCount());
    }

    @Override
    protected String getVertexSymbol(int atomIndex) {
        // XXX this is the method with the key difference...
        IAtom atom = atomContainer.getAtom(atomIndex);
//        Double charge = atom.getCharge();
        Integer charge = atom.getFormalCharge();
        if (charge == null) {
            return atom.getSymbol();
        } else {
            return atom.getSymbol() + charge;
        }
    }

    @Override
    protected int[] getConnected(int atomIndex) {
        List<IAtom> connectedAtoms = atomContainer.getConnectedAtomsList(
                atomContainer.getAtom(atomIndex));
        int[] connected = new int[connectedAtoms.size()];
        int i = 0;
        for (IAtom connectedAtom : connectedAtoms) {
            connected[i] = atomContainer.getAtomNumber(connectedAtom);
            i++;
        }
        return connected;
    }

    @Override
    protected String getEdgeLabel(int atomIndexA, int atomIndexB) {
        IAtom atomA = atomContainer.getAtom(atomIndexA);
        IAtom atomB = atomContainer.getAtom(atomIndexB);
        IBond bond = atomContainer.getBond(atomA, atomB); 
        switch (bond.getOrder()) {
            case SINGLE:  return "";
            case DOUBLE:  return "=";
            case TRIPLE:  return "#";
            case QUADRUPLE:  return "$";
            default: return "";
        }
    }

    @Override
    protected int getIntLabel(int atomIndex) {
        // TODO Auto-generated method stub
        return 0;
    }

}
