package frc.team2158.robot.command.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2158.robot.Robot;

public class StopLift extends Command {
    public StopLift() {
        requires(Robot.getLiftSubsystem());
    }

    @Override
    protected void initialize() {
        Robot.getLiftSubsystem().stopLift();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
