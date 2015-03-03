



public class HW2 {

    public static void main (String[] args){
        System.out.println("\nBeginning HW2 - Part I");
        
        Two twoObject = new Two();
        twoObject.print();
        twoObject.print2();
        
        System.out.println("\nBeginning HW2 - Part II");
        
        One anObject = new Two();
        anObject.print();
        //anObject.print2();  //ERROR!!!!
        
        System.out.println("\nBeginning HW2 - Part III");
        
        One anotherObject = new Two();
        
        ((One)anotherObject).print();  //QC
        
        System.out.println("\nBeginning HW2 - Part IV");
        

    
    }
}
