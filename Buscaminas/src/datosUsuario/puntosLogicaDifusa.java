package datosUsuario;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.Gpr;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class puntosLogicaDifusa {
	public static void main(String[] args) {
		System.out.println("Hola");
		
		 // Load from 'FCL' file
        String fileName = "flc/PuntajeBuscaminas.fcl";
        
        FIS fis = FIS.load(fileName,true);
        // Error while loading?
        
        if( fis == null ) { 
            System.err.println("Can't load file: '" 
                                   + fileName + "'");
            return;
        }
   
        //Show Ruleset
        FunctionBlock functionBlock = fis.getFunctionBlock(null);
        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        fis.setVariable("time", 0);
        fis.setVariable("mistakes", 0);
        fis.setVariable("difficult", 3);
        fis.setVariable("level", 5);


        // Evaluate
        fis.evaluate();
       
        Variable tip = functionBlock.getVariable("points");
        JFuzzyChart.get().chart(tip, tip.getDefuzzifier(),true);
        double valor1 = functionBlock.getVariable("points").getMembership("low");
        double valor2 = functionBlock.getVariable("points").getMembership("average");
        double valor3 = functionBlock.getVariable("points").getMembership("high");
        
        System.out.println("Valor 1:"+valor1);
        System.out.println("Valor 2:"+valor2);
        System.out.println("Valor 3:"+valor3);
        
        Gpr.debug("low[points]: "+ functionBlock.getVariable("points").getMembership("low"));
        Gpr.debug("average[points]: "+ functionBlock.getVariable("points").getMembership("average"));
        Gpr.debug("high[points]: "+ functionBlock.getVariable("points").getMembership("high"));
        
        System.out.println("points: "+functionBlock.getVariable("points").getValue());
        // Show output variable's chart 
        

	}

}
