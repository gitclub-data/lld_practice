abstract class MoneyHandler{

    protected MoneyHandler nexthandler;
    protected Integer denomination;
    protected Integer noOfNotes;

    protected MoneyHandler(Integer denomination, Integer noOfNotes){
        this.denomination = denomination;
        this.noOfNotes = noOfNotes;
    }

    public void setHandler(MoneyHandler handler){
        this.nexthandler =  handler;
    }

    protected void getmoney(Integer rstoget) throws Exception{
        Integer noOfnotesrequired  = Integer.valueOf(rstoget/denomination);
        if(noOfnotesrequired<noOfNotes){
            noOfNotes -= noOfnotesrequired;
            rstoget -= noOfnotesrequired*denomination;
            if(rstoget>0){
                if(this.nexthandler==null){
                    throw new Exception("Cannot dispense the required amount");
                }else{
                    try{
                        nexthandler.getmoney(rstoget);
                    }catch(Exception e){
                        throw e;
                    }
                }
            }
            if(noOfnotesrequired>0){
                System.out.println("getting out "+noOfnotesrequired+"x "+denomination+" notes");
            }
        }else{
            if(this.nexthandler==null){
                throw new Exception("Cannot dispense the required amount");
            }else{
                try{
                    nexthandler.getmoney(rstoget - (noOfNotes*denomination));
                    System.out.println("getting out "+noOfNotes+"x "+denomination+" notes");
                    rstoget -= noOfNotes*denomination;
                    noOfNotes = 0;
                }catch(Exception e){
                    throw e;
                }
            }
        }
    }

    public abstract void despenseMoney(Integer rstoget);
}

class HundredRsHandler extends MoneyHandler{
    public HundredRsHandler(Integer noOfNotes){
        super(100,noOfNotes);
    }

    @Override
    public void despenseMoney(Integer rstoget){
        try{
            super.getmoney(rstoget);
        }catch(Exception e){    
            System.out.println(e.getMessage());
        }
    }
}

class ThousandRsHandler extends MoneyHandler{
    public ThousandRsHandler(Integer noOfNotes){
        super(1000,noOfNotes);
    }

    @Override
    public void despenseMoney(Integer rstoget){
        try{
            super.getmoney(rstoget);
        }catch(Exception e){    
            System.out.println(e.getMessage());
        }
    }
}

class FiftyRsHandler extends MoneyHandler{
    public FiftyRsHandler(Integer noOfNotes){
        super(50,noOfNotes);
    }

    @Override
    public void despenseMoney(Integer rstoget){
        try{
            super.getmoney(rstoget);
        }catch(Exception e){    
            System.out.println(e.getMessage());
        }
    } 
}


class TenRsHandler extends MoneyHandler {

    public TenRsHandler(Integer noOfNotes){
        super(10,noOfNotes);
    }

    @Override
    public void despenseMoney(Integer rstoget){
        try{
            super.getmoney(rstoget);
        }catch(Exception e){    
            System.out.println(e.getMessage());
        }
    }

}

public class ATMDispenser {
    public static void main(String[] args) {
        
        MoneyHandler thousands = new ThousandRsHandler(5);
        MoneyHandler hundereds = new HundredRsHandler(10);
        MoneyHandler fiftys = new FiftyRsHandler(10);
        MoneyHandler tens = new TenRsHandler(10);

        thousands.setHandler(hundereds);
        hundereds.setHandler(fiftys);
        fiftys.setHandler(tens);

        System.out.println("Please enter the amount to withdraw in multiples of 10");
        Integer amountToWithdraw = 6380;
        System.out.println("You have Entered the amount as "+ amountToWithdraw);
        
        thousands.despenseMoney(amountToWithdraw);

    }
}