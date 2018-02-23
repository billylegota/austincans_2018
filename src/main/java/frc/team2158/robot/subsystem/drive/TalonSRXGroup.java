package frc.team2158.robot.subsystem.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;

public class TalonSRXGroup implements SpeedController {
    private WPI_TalonSRX master;

    /**
     * Creates a new TalonSRXGroup from a set of WPI_TalonSRX instances. The first instance is used as the master.
     * @param master the master motor. All other motors will follow this one.
     * @param slaves the slave motors. Follow the master motor.
     */
    public TalonSRXGroup(WPI_TalonSRX master, WPI_TalonSRX... slaves) {
        this.master = master;

        master.configContinuousCurrentLimit(15, 0);
        master.configPeakCurrentLimit(20, 0);
        master.configPeakCurrentDuration(250, 0);
        master.configOpenloopRamp(0.250, 0);
        master.configClosedloopRamp(0.250, 0);
        master.enableCurrentLimit(true);
        master.config_kP(0, 0.4, 0);
        master.config_kI(0, 0.0, 0);
        master.config_kD(0, 0.0, 0);

        for(WPI_TalonSRX slave : slaves) {
            slave.follow(master);
        }
    }

    /**
     * Set the output to the value calculated by PIDController.
     * @param output the value calculated by PIDController.
     */
    @Override
    public void pidWrite(double output) {
        master.pidWrite(output);
    }

    /**
     * Common interface for setting the speed of a speed controller.
     * @param speed the speed to set. Value should be between -1.0 and 1.0.
     */
    @Override
    public void set(double speed) {
        master.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Common interface for getting the current set speed of the speed controller.
     * @return the current set speed. Value is between -1.0 and 1.0.
     */
    @Override
    public double get() {
        return master.get();
    }

    /**
     * Common interface for inverting direction of a speed controller.
     * @param isInverted the state of inversion; true is inverted.
     */
    @Override
    public void setInverted(boolean isInverted) {
        master.setInverted(isInverted);
    }

    /**
     * Common interface for returning if a speed controller is in the inverted state or not.
     * @return the state of the inversion; true is inverted.
     */
    @Override
    public boolean getInverted() {
        return master.getInverted();
    }

    /**
     * Disable the speed controller.
     */
    @Override
    public void disable() {
        master.disable();
    }

    /**
     * Stops motor movement. Motor can be moved again by calling set without having to re-enable the motor.
     */
    @Override
    public void stopMotor() {
        master.stopMotor();
    }
}
