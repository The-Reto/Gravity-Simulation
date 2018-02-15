package graph.Windows;

import graph.Graphics.DrawableWorld;
import graph.Graphics.WorldView;
import graph.UI.GeneralUI;
import graph.UI.PhysicsUI;
import graph.UI.UIpanel;
import graph.UI.UIview;
import phys.Essentials.PhysicsEvent;
import phys.PhysicsWorld;

import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends Window {

	private static final int size_UI = 675;

	public MainWindow(String _title, int size_x, int size_y, PhysicsWorld world) {
		super(size_x + size_UI,size_y);
		this.setTitle(_title);
		this.main = true;
		this.setLayout(new BorderLayout());

		WorldView worldView = new WorldView(world);

		ArrayList<UIpanel> list = new ArrayList<>();
		list.add(new PhysicsUI(world));
		list.add(new GeneralUI(worldView));
		graph.UI.UIview UIview = new UIview(list, size_UI, size_Y);

		this.add(UIview, BorderLayout.EAST);
		this.add(worldView, BorderLayout.CENTER);
		this.setVisible(true);
		worldView.toggleAnimation();

		new PhysicsEvent(PhysicsEvent.EventType.READY, "Main Window now Ready!", PhysicsWorld.timeElapsed);

	}

	public MainWindow(String _title, PhysicsWorld world )
	{
		this(_title, world.getSize_X(),  world.getSize_X(), world);
	}

	public void redoWindow() {
		this.setSize(size_X + size_UI, size_Y);
		this.setVisible(true);
	}
}
