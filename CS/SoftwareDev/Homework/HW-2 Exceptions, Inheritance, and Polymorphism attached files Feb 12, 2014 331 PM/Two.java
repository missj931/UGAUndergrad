public class Two extends One{
        String twoString = "Get it?";
        
        public Two() {
            System.out.println("Two's Constructor");
        }
        
        public void print() {
            System.out.println(twoString);
        }
        
        public void print2() {
            super.print();
            System.out.println(twoString);
        }
    }
