package frc.team2158.robot.command.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2158.robot.Robot;

public class ToggleGearMode extends Command {
    @Override
    protected void initialize() {
        Robot.getDriveSubsystem().toggleGearMode();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
