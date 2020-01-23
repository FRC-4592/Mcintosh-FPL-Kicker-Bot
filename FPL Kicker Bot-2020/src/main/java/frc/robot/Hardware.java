package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Util.doubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;

public class Hardware {
    // Drivetrain Motors
    public static final WPI_VictorSPX rightMasterMotor = new WPI_VictorSPX(Constants.RIGHT_MASTER_MOTOR_CAN);
    public static final WPI_VictorSPX rightSlaveMotor = new WPI_VictorSPX(Constants.RIGHT_SLAVE_MOTOR_CAN);
	public static final WPI_VictorSPX leftMasterMotor = new WPI_VictorSPX(Constants.LEFT_MASTER_MOTOR_CAN);
    public static final WPI_VictorSPX leftSlaveMotor = new WPI_VictorSPX(Constants.LEFT_SLAVE_MOTOR_CAN);
    
    // Drivetrain Shifter
    public static final doubleSolenoid shifter = new doubleSolenoid(Constants.SHIFTER_OPEN, Constants.SHIFTER_CLOSE);

    //Arm Motor
    public static final WPI_TalonSRX ArmMasterMotor = new WPI_TalonSRX(Constants.ARM_MASTER_MOTOR_CAN);
    public static final WPI_VictorSPX ArmSlaveMotor = new WPI_VictorSPX(Constants.ARM_SLAVE_MOTOR_CAN);

    //Arm Shifter
    public static final doubleSolenoid shifter2 = new doubleSolenoid(Constants.SHIFTER2_OPEN, Constants.SHIFTER2_CLOSE);

    //Joystick
    public static final Joystick driverPad = new Joystick(Constants.DRIVE_USB_PORT);

    //Limit Switch
    public static final DigitalInput limiter = new DigitalInput(Constants.LIMITER);

}