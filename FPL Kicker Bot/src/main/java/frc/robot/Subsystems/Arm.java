package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Util.doubleSolenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.Hardware;
import frc.robot.Lib.SubsystemFramework;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Arm extends SubsystemFramework {
    // Arm Motors
    private WPI_TalonSRX ArmMasterMotor;
    private WPI_VictorSPX ArmSlaveMotor;

    // Arm Shifter
    private doubleSolenoid shifter2;
    
    //Limit Switch
    private DigitalInput limiter;
    
    public Arm(WPI_TalonSRX ArmMasterMotor, WPI_VictorSPX ArmSlaveMotor, doubleSolenoid shifter2, DigitalInput limiter) {
        // Arm Motors 
        this.ArmMasterMotor = ArmMasterMotor;
        this.ArmSlaveMotor = ArmSlaveMotor;
        
        // Arm Shifter
        this.shifter2 = shifter2;

        // Limit Switch
        this.limiter = limiter;
    }

    public static boolean ArmPower() {
        return Hardware.driverPad.getRawButton(Constants.ARM_POWER);
    }
    public static boolean Release() {
        return Hardware.driverPad.getRawButton(Constants.RELEASE);
    }
    @Override
    public void update() {
        if(ArmPower()) {
            shifter2.close();
            ArmMasterMotor.set(0.5);
            if(limiter.get()) {
                ArmMasterMotor.set(0);
            }
        }
        else {
            ArmMasterMotor.set(0);
        }
        if(Release()){
            shifter2.open();
        }

    }
    
    @Override
    public void outputToSmartDashboard() {
        
    }

    @Override
    public void setupSensors() {
        // Setup Master Slave Relationship
        ArmSlaveMotor.follow(ArmMasterMotor);
        ArmSlaveMotor.setInverted(true);
						
		// Setup Master Encoders
		ArmMasterMotor.setSensorPhase(true);
						
		// This initializes where the Encoder values come from
		ArmMasterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		// This sets Encoder values to 0 when robot begins
		ArmMasterMotor.setSelectedSensorPosition(0, 0, 10);
				
		// Configures the Speed of Each motor
		ArmMasterMotor.configNominalOutputForward(0, 10);
		ArmMasterMotor.configNominalOutputReverse(0, 10);
		ArmMasterMotor.configPeakOutputForward(1, 10);
		ArmMasterMotor.configPeakOutputReverse(1, 10);

    }
}
