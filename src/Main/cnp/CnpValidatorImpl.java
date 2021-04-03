package Main.cnp;

import java.util.ArrayList;

public class CnpValidatorImpl implements CnpValidator {

    private CnpParts cnpParts = new CnpPartsImpl();
    private String cnp;
    private ArrayList<Byte> cnpArray = new ArrayList<Byte>();
    private ArrayList<Byte> cArray = new ArrayList<>();
    private Byte C;
    private Boolean cValid;

    public CnpValidatorImpl() {
    }

    /**
     * Se verifica lungimea CNP-ului si se arunca o exceptie daca este incorecta.
     * Se transforma CNP-ul in array pentru a se prelucra si se verifica daca este valid.
     * Se populeaza campurile CnpParts.
     */
    @Override
    public CnpParts validateCnp(String cnp) throws CnpException {
        if (cnp.length() != 13) throw new CnpException("CNP needs to have 13 digits !");
        this.cnp = cnp;

        cnpToArray();
        cToArray();
        cifraControl();
        cValid();
        if (!cValid) throw new CnpException("Invalid CNP");

        sex();
        foreigner();
        birthDate();
        judet();
        orderNumber();
        return cnpParts;
    }

    /**
     * Se atribuie sexul in functie de prima cifra din CNP.
     */
    private void sex() {
        if (cnpArray.get(0) % 2 == 0) cnpParts.setSex(Sex.F);
        else if (cnpArray.get(0) < 9 && cnpArray.get(0) % 2 == 1) cnpParts.setSex(Sex.M);
        else cnpParts.setSex(Sex.U);
    }

    /**
     * Se verifica daca CNP-ul corespunde unui cetatean roman.
     * Rezidentii sunt considerati romani.
     */
    private void foreigner() {
        if (cnpArray.get(0) == 9) cnpParts.setForeigner(true); //Residents are not considered foreigners
        else cnpParts.setForeigner(false);
    }

    /**
     * Se foloseste o instanta a implementarii CalDate pentru a atribui data de nastere.
     * Rezidentii/strainii sunt considerati a fi nascuti dupa anul 2000.
     */
    private void birthDate() {
        CalDateImpl calDate = new CalDateImpl();

        byte sex = cnpArray.get(0);
        int year = cnpArray.get(1) * 10 + cnpArray.get(2);

        if (sex == 1 || sex == 2) {
            calDate.setYear((short) (1900 + year));
        } else if (sex == 3 || sex == 4) {
            calDate.setYear((short) (1800 + year));
        } else if (sex == 5 || sex == 6) {
            calDate.setYear((short) (2000 + year));
        } else if (sex == 7 || sex == 8 || sex == 9) {
            calDate.setYear((short) (2000 + year));
        }
        calDate.setMonth((byte) (cnpArray.get(3) * 10 + cnpArray.get(4)));
        calDate.setDay((byte) (cnpArray.get(5) * 10 + cnpArray.get(6)));

        cnpParts.setBirthDate(calDate);
    }

    /**
     * Se atribuie judetul(cu o metoda destul de putin eleganta)
     */
    private void judet() {
        byte code = (byte) (cnpArray.get(7) * 10 + cnpArray.get(8));

        switch (code) {
            case 1: {
                cnpParts.setJudet(Judet.AB);
                break;
            }
            case 2: {
                cnpParts.setJudet(Judet.AR);
                break;
            }
            case 3: {
                cnpParts.setJudet(Judet.AG);
                break;
            }
            case 4: {
                cnpParts.setJudet(Judet.BC);
                break;
            }
            case 5: {
                cnpParts.setJudet(Judet.BH);
                break;
            }
            case 6: {
                cnpParts.setJudet(Judet.BN);
                break;
            }
            case 7: {
                cnpParts.setJudet(Judet.BT);
                break;
            }
            case 8: {
                cnpParts.setJudet(Judet.BV);
                break;
            }
            case 9: {
                cnpParts.setJudet(Judet.BR);
                break;
            }
            case 10: {
                cnpParts.setJudet(Judet.BZ);
                break;
            }
            case 11: {
                cnpParts.setJudet(Judet.CS);
                break;
            }
            case 12: {
                cnpParts.setJudet(Judet.CJ);
                break;
            }
            case 13: {
                cnpParts.setJudet(Judet.CO);
                break;
            }
            case 14: {
                cnpParts.setJudet(Judet.CV);
                break;
            }
            case 15: {
                cnpParts.setJudet(Judet.DB);
                break;
            }
            case 16: {
                cnpParts.setJudet(Judet.DJ);
                break;
            }
            case 17: {
                cnpParts.setJudet(Judet.GL);
                break;
            }
            case 18: {
                cnpParts.setJudet(Judet.GJ);
                break;
            }
            case 19: {
                cnpParts.setJudet(Judet.HG);
                break;
            }
            case 20: {
                cnpParts.setJudet(Judet.HD);
                break;
            }
            case 21: {
                cnpParts.setJudet(Judet.IL);
                break;
            }
            case 22: {
                cnpParts.setJudet(Judet.IS);
                break;
            }
            case 23: {
                cnpParts.setJudet(Judet.IF);
                break;
            }
            case 24: {
                cnpParts.setJudet(Judet.MM);
                break;
            }
            case 25: {
                cnpParts.setJudet(Judet.MH);
                break;
            }
            case 26: {
                cnpParts.setJudet(Judet.MS);
                break;
            }
            case 27: {
                cnpParts.setJudet(Judet.NT);
                break;
            }
            case 28: {
                cnpParts.setJudet(Judet.OT);
                break;
            }
            case 29: {
                cnpParts.setJudet(Judet.PH);
                break;
            }
            case 30: {
                cnpParts.setJudet(Judet.SM);
                break;
            }
            case 31: {
                cnpParts.setJudet(Judet.SJ);
                break;
            }
            case 32: {
                cnpParts.setJudet(Judet.SB);
                break;
            }
            case 33: {
                cnpParts.setJudet(Judet.SV);
                break;
            }
            case 34: {
                cnpParts.setJudet(Judet.TR);
                break;
            }
            case 35: {
                cnpParts.setJudet(Judet.TM);
                break;
            }
            case 36: {
                cnpParts.setJudet(Judet.TL);
                break;
            }
            case 37: {
                cnpParts.setJudet(Judet.VS);
                break;
            }
            case 38: {
                cnpParts.setJudet(Judet.VL);
                break;
            }
            case 39: {
                cnpParts.setJudet(Judet.VN);
                break;
            }
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 47: {
                cnpParts.setJudet(Judet.BU);
                break;
            }
            case 51: {
                cnpParts.setJudet(Judet.CL);
                break;
            }
            case 52: {
                cnpParts.setJudet(Judet.GR);
                break;
            }
            default: {
                System.out.println("Unknown CNP");
            }

        } // Exception incorrect Judet
    }

    /**
     * Se atribuie numarul secvential prin care se diferentiaza persoanele de acelasi sex, nascute
     * in acelasi loc si cu aceeasi data de nastere.
     */
    private void orderNumber() {
        int orderNumber = cnpArray.get(9) * 100 + cnpArray.get(10) * 10 + cnpArray.get(11);
        cnpParts.setOrderNumber(orderNumber);
    }

    /**
     * Se calculeaza cifra de control corespunzatoare CNP-ului dupa pasii :
     * 1.Fiecare cifră din primele 12 cifre ale C.N.P. este înmulțită cu corespondentul său din constanta : 279146358279
     * 2.Rezultate sunt însumate și totalul se împarte la 11
     * 3.Dacă restul împărțirii este mai mic de 10, acela reprezintă valoarea componentei C
     * 4.Dacă restul împărțirii este 10, valoarea componentei C este 1
     */
    private void cifraControl() {

        int sum = 0;
        for (int i = 0; i < cArray.size(); i++) {
            sum += cArray.get(i) * cnpArray.get(i);
        }
        if (sum % 11 < 10) C = (byte) (sum % 11);
        else C = 1;
    }

    private void cnpToArray() {
        for (int i = 0; i < cnp.length(); i++) {
            cnpArray.add(i, Byte.parseByte(String.valueOf(cnp.charAt(i))));
        }
    }

    private void cToArray() {
        String c = "279146358279";
        for (int i = 0; i < c.length(); i++) {
            cArray.add(i, Byte.parseByte(String.valueOf(c.charAt(i))));
        }
    }

    /**
     * Returneaza daca CNP-ul este valid sau nu
     */
    private void cValid() {
        cValid = cnpArray.get(12).equals(C);
    }

}
