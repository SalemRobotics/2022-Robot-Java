package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexIn extends CommandBase {
    private final Indexer indexer;
    public IndexIn(Indexer subsystem) {
        indexer = subsystem;
        addRequirements(indexer);
    }

    @Override
    public void execute() {
        indexer.indexIn();
    }

    @Override
    public void end(boolean interrupted) {
        indexer.halt();
    }
}
