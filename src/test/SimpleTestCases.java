package test;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;
import org.openscience.cdk.graph.AtomContainerAtomPermutor;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.nonotify.NoNotificationChemObjectBuilder;

import chargedsigs.ChargedMoleculeSignature;

public class SimpleTestCases {
    
    private IChemObjectBuilder builder = 
        NoNotificationChemObjectBuilder.getInstance();
    
    public void testMolecule(IAtomContainer mol) {
        ChargedMoleculeSignature molSig = new ChargedMoleculeSignature(mol);
        String sigString = molSig.toCanonicalString();
        int[] labels = molSig.getCanonicalLabels();
        System.out.println(sigString + " " + Arrays.toString(labels));
    }
    
    public void permutationTest(IAtomContainer mol) {
        ChargedMoleculeSignature molSig = new ChargedMoleculeSignature(mol);
        String originalSigString = molSig.toCanonicalString();
        AtomContainerAtomPermutor permutor = new AtomContainerAtomPermutor(mol);
        while (permutor.hasNext()) {
            IAtomContainer permutedContainer = permutor.next();
            ChargedMoleculeSignature permutedMolSig = 
                new ChargedMoleculeSignature(permutedContainer);
            String permutedSigString = permutedMolSig.toCanonicalString();
            System.out.println(permutedSigString);
            Assert.assertEquals(originalSigString, permutedSigString);
        }
    }
    
    @Test
    public void phosphateTest() {
        IAtomContainer phos = builder.newInstance(IAtomContainer.class);
        phos.addAtom(builder.newInstance(IAtom.class, "P"));
        phos.addAtom(builder.newInstance(IAtom.class, "O"));
        phos.addAtom(builder.newInstance(IAtom.class, "O"));
        phos.addAtom(builder.newInstance(IAtom.class, "O"));
        phos.addAtom(builder.newInstance(IAtom.class, "O"));
        phos.addBond(0, 1, IBond.Order.SINGLE);
        phos.addBond(0, 2, IBond.Order.DOUBLE);
        phos.addBond(0, 3, IBond.Order.SINGLE);
        phos.addBond(0, 4, IBond.Order.DOUBLE);
        
//        phos.getAtom(1).setCharge(-1.0);
        phos.getAtom(1).setFormalCharge(-1);
        
//        testMolecule(phos);
        permutationTest(phos);
    }
    
    @Test
    public void imidazoleTest() {
        IAtomContainer imidazole = builder.newInstance(IAtomContainer.class);
        imidazole.addAtom(builder.newInstance(IAtom.class, "C"));
        imidazole.addAtom(builder.newInstance(IAtom.class, "C"));
        imidazole.addAtom(builder.newInstance(IAtom.class, "N"));
        imidazole.addAtom(builder.newInstance(IAtom.class, "C"));
        imidazole.addAtom(builder.newInstance(IAtom.class, "N"));
        imidazole.addBond(0, 1, IBond.Order.SINGLE);
        imidazole.addBond(0, 4, IBond.Order.DOUBLE);
        imidazole.addBond(1, 2, IBond.Order.DOUBLE);
        imidazole.addBond(2, 3, IBond.Order.SINGLE);
        imidazole.addBond(3, 4, IBond.Order.SINGLE);
        
//        imidazole.getAtom(2).setCharge(1.0);
//        imidazole.getAtom(3).setCharge(-1.0);
        imidazole.getAtom(2).setFormalCharge(1);
        imidazole.getAtom(3).setFormalCharge(-1);

        
//        testMolecule(imidazole);
        permutationTest(imidazole);
    }

}
