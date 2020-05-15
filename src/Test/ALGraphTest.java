package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.ALGraph;
import model.Edge;
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
	private void stage2() {
		Station s1;
		Station s2;
		Station s3;
		Station s4;
		Station s5;
		Station s6;
		m = new ALGraph<Station>();
		s1 = new Station("a");
		s2 = new Station("b");
		s3 = new Station("c");
		s4 = new Station("d");
		s5 = new Station("e");
		s6 = new Station("z");
		m.add(s1);
		m.add(s2);
		m.add(s3);
		m.add(s4);
		m.add(s5);
		m.add(s6);
		m.connect(s1, s2, 4);
		m.connect(s1, s4, 2);
		m.connect(s2, s3, 3);
		m.connect(s2, s5, 3);
		m.connect(s4, s5, 3);
		m.connect(s3, s6, 2);
		m.connect(s5, s6, 1);
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
		stage1();
		
	}
	@Test 
	void kruskalTest() {
		int camino =0;
		stage1();
		List<Edge<Station>> m1 =  m.Kruskal();
		for (int i = 0; i < m1.size(); i++) {
			camino += m1.get(i).getWeight();
		}
		assertTrue(camino==43);
		
		
	}
	@Test
	void dijkstraTest() {
		stage2();
		m.Dijkstra(m.getVertexs().get(0).getObject());
		int t = m.getVertexs().get(0).getDistance();
		assertTrue(t==0);
	}
}
