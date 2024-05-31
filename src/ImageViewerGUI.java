import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton = new JButton("Choose Image");

    JButton showImageButton = new JButton("Show Image");
    JButton resizeButton = new JButton("Resize");
    JButton grayscaleButton = new JButton("Gray Scale");
    JButton brightnessButton = new JButton("Brightness");
    JButton closeButton = new JButton("Exit");
    ImageIcon imageIcon;
    JButton showResizeButton = new JButton("Show Result");;
    JButton showBrightnessButton = new JButton("Result");
    JButton backButton = new JButton("Back");
    JTextField widthTextField = new JTextField();
    JTextField heightTextField = new JTextField();
    JTextField brightnessTextField = new JTextField();
    JLabel text = new JLabel("Enter f \n (must be between 0 and 1)");
    String filePath = "/Users/mhd84.ir/uni/image_viewer/out/Images";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    ImageViewerGUI(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
    }

    public void mainPanel(){
        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));


        buttonsPanel.setBounds(100,100,500,125);

        JLabel imageViewer = new JLabel("Image Viewer");
        imageViewer.setBounds(310,60,100,30);

//         Adding all buttons to Grid panel

        buttonsPanel.add(this.selectFileButton);
        this.selectFileButton.addActionListener(this);
        buttonsPanel.add(this.showImageButton);
        this.showImageButton.addActionListener(this);
        buttonsPanel.add(this.brightnessButton);
        this.brightnessButton.addActionListener(this);
        buttonsPanel.add(this.grayscaleButton);
        this.grayscaleButton.addActionListener(this);
        buttonsPanel.add(this.resizeButton);
        this.resizeButton.addActionListener(this);
        buttonsPanel.add(this.closeButton);
        this.closeButton.addActionListener(this);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(imageViewer);
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);
    }

    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);

        JLabel l = new JLabel("Resize Section");
        l.setBounds(300,50,100,20);
        resizePanel.add(l);

        widthTextField.setBounds(300,80,100,30);
        resizePanel.add(widthTextField);

        heightTextField.setBounds(300,100,100,30);
        resizePanel.add(heightTextField);

        JLabel width = new JLabel("Width :");
        JLabel height = new JLabel("Height :");

        width.setBounds(150,80,50,30);
        height.setBounds(150,100,50,30);
        this.showResizeButton.setBounds(500,200,100,50);
        this.backButton.setBounds(50,200,100,50);

        resizePanel.add(width);
        resizePanel.add(height);
        resizePanel.add(backButton);
        resizePanel.add(showResizeButton);

        backButton.addActionListener(this);
        showResizeButton.addActionListener(this);




        this.add(resizePanel);
    }
    public void brightnessPanel(){


        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);





        this.brightnessTextField.setBounds(300,100,200,40);
        brightnessPanel.add(this.brightnessTextField);


        this.text.setBounds(25,100,275,40);
        brightnessPanel.add(this.text);



        this.backButton.setBounds(50,200,100,50);
        this.backButton.addActionListener(this);
        brightnessPanel.add(this.backButton);

        this.showBrightnessButton.setBounds(500,200,100,50);
        this.showBrightnessButton.addActionListener(this);
        brightnessPanel.add(this.showBrightnessButton);

        this.add(brightnessPanel);



    }

    public void chooseFileImage(){



        fileChooser.setAcceptAllFileFilterUsed(false);

        int option = fileChooser.showOpenDialog(ImageViewerGUI.this);
        if (option == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();
            imageIcon = new ImageIcon(new ImageIcon (String.valueOf(file)).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));

        }



    }
    public void showOriginalImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();


        JLabel pictureLabel = new JLabel();
        pictureLabel.setIcon(imageIcon);
        tempPanel.add(pictureLabel);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        Image image =imageIcon.getImage();

        ImageFilter filter = new GrayFilter(true, 50);
        ImageProducer producer = new FilteredImageSource(image.getSource(), filter);
        Image mage = Toolkit.getDefaultToolkit().createImage(producer);

        JLabel grayScaleImage = new JLabel();
        grayScaleImage.setIcon(new ImageIcon(mage));

        tempPanel.add(grayScaleImage);




        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showResizeImage(int w, int h){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        Image i = imageIcon.getImage();

        Image newImage = i.getScaledInstance(w, h, Image.SCALE_DEFAULT);

        imageIcon.setImage(newImage);

        JLabel la = new JLabel();

        la.setIcon(imageIcon);
        tempFrame.add(la);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showBrightnessImage (float f) throws IOException  {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        BufferedImage bufferedImage1 = ImageIO.read(file);
        RescaleOp op = new RescaleOp(f, 0, null);
        BufferedImage image = op.filter(bufferedImage1 , null);

        ImageIcon picture = new ImageIcon(image);

        JLabel pictureLabel = new JLabel();

        pictureLabel.setIcon(picture);

        tempPanel.add(pictureLabel);


        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            this.getContentPane().removeAll();
            resizePanel();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()== showImageButton){
            showOriginalImage();
        }else if(e.getSource()==grayscaleButton){
            grayScaleImage();

        }else if(e.getSource()== showResizeButton){

            h = Integer.parseInt(heightTextField.getText());
            w = Integer.parseInt(widthTextField.getText());
            showResizeImage(w,h);

        }else if(e.getSource()==brightnessButton){

            this.getContentPane().removeAll();
            this.brightnessPanel();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()== showBrightnessButton){

            try {
                brightenFactor = Float.parseFloat(this.brightnessTextField.getText());

                showBrightnessImage(brightenFactor);

            }
            catch (IOException ee)
            {
                System.out.println("error");
            }
            catch (NumberFormatException ee)
            {
                System.out.println("2");
            }



        }else if(e.getSource()== selectFileButton){
            chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}