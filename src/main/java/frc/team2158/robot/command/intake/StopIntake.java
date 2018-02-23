package frc.team2158.robot.command.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2158.robot.Robot;

public class StopIntake extends Command {
    public StopIntake() {
        requires(Robot.getIntakeSubsystem());
    }

    @Override
    protected void initialize() {
        Robot.getIntakeSubsystem().stopIntake();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
