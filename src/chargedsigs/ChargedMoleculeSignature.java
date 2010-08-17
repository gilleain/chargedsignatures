package chargedsigs;

import org.openscience.cdk.interfaces.IAtomContainer;

import signature.AbstractGraphSignature;
import signature.AbstractVertexSignature;

public class ChargedMoleculeSignature extends AbstractGraphSignature {
    
private IAtomContainer atomContainer;
    
    public ChargedMoleculeSignature(IAtomContainer atomContainer) {
        this.atomContainer = atomContainer;
    }

    @Override
    protected int getVertexCount() {
        return atomContainer.getAtomCount();
    }

    @Override
    public AbstractVertexSignature signatureForVertex(int atomIndex) {
        return new ChargedAtomSignature(atomIndex, atomContainer);
    }

    @Override
    public String signatureStringForVertex(int atomIndex) {
        return new ChargedAtomSignature(
                atomIndex, atomContainer).toCanonicalString();
    }

    @Override
    public String signatureStringForVertex(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

}
