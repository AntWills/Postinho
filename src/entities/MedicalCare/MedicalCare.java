package entities.MedicalCare;

public class MedicalCare {
    /**
     * # data reading #
     * <p>
     * number form : type servive : color form
     * <p>
     * (0) : [NOT URGENT] : Blue
     * <P>
     * (1) : [LITTLE URGENT] : Green
     * <p>
     * (2) : [URGENT] : Yellow
     */
    private int typeService;
    private Date dateService;
    private String reasonForService;

    public MedicalCare() {
        this.typeService = 0;
        this.dateService = new Date();
        this.reasonForService = "";
    }

    public MedicalCare(int typeService, Date dateService, String reasonForService) {
        this.typeService = typeService;
        this.dateService = dateService;
        this.reasonForService = reasonForService;
    }
}
