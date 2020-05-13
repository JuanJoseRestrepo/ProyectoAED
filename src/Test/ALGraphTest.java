package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.ALGraph;
import model.Station;

public class ALGraphTest {
	
	private ALGraph<Station> m;
	
	private void stage1() {
		Station s1;
		Station s2;
		Station s3;
		Station s4;
		Station s5;
		m = new ALGraph<Station>();
		s1 = new Station("1");
		s2 = new Station("2");
		s3 = new Station("3");
		s4 = new Station("4");
		s5 = new Station("5");
		m.add(s1);
		m.add(s2);
		m.add(s3);
		m.add(s4);
		m.add(s5);
		m.connect(s1, s2, 5);
		m.connect(s1, s3, 7);
		m.connect(s2, s3, 12);
		m.connect(s2, s4, 17);
		m.connect(s2, s5, 16);
		m.connect(s3, s4, 15);
		m.connect(s5, s4, 20);
	}

	@Test
	void DFSTest() {
		stage1();
		m.DFS();
		assertTrue(m.getVertexs().get(0).getDistance() == 1 && m.getVertexs().get(0).getF() == 10);
	}
	@Test
	void BFSTest() {
		stage1();
		m.BFS(m.getVertexs().get(0).getObject());
		assertTrue(m.getVertexs().get(0).getDistance()==0);
	}
	@Test
	void floydWarshallTest() {
		
	}
}
