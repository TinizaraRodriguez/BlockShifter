
package blockshifter.apps.swing;

import blockshifter.control.BlockPresenter;
import blockshifter.control.Command;
import blockshifter.control.DownCommand;
import blockshifter.control.LeftCommand;
import blockshifter.control.RightCommand;
import blockshifter.control.UpCommand;
import blockshifter.view.BlockDisplay;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import blockshifter.model.Block;
import java.awt.FlowLayout;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Main extends JFrame {
    private Block block;
    private BlockDisplay blockDisplay;
    Map<String, Command> commands;
    public static void main(String[] args) {
        new Main().execute();
    }
    private BlockPresenter blockPresenter;
    
    public Main(){
        this.setTitle("Block shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(717,779);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(),BorderLayout.SOUTH);
    }

    private void execute() {
        this.block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands = createCommands();
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel panel = new BlockPanel(Block.MAX);
        this.blockDisplay = panel;
        return panel;
    }
    
    private Map<String,Command> createCommands(){
        Map<String, Command> commands = new HashMap<>();
        commands.put("Arriba", new UpCommand(block));
        commands.put("Abajo", new DownCommand(block));
        commands.put("Izquierda", new LeftCommand(block));
        commands.put("Derecha", new RightCommand(block));
        return commands;
    }

    private JMenuBar toolbar() {
        JMenuBar result = new JMenuBar();
        result.setLayout(new FlowLayout(FlowLayout.CENTER));
        result.add(button("Arriba"));
        result.add(button("Abajo"));
        result.add(button("Izquierda"));
        result.add(button("Derecha"));
        return result;
    }

    private JButton button(String command) {
        JButton button = new JButton(command);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                commands.get(command).execute();
            }
        });
        return button;
    }
}
