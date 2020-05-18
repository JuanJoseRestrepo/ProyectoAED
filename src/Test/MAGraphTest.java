package test;
import model.MAGraph;
import model.Station;
import model.VertexM;
import model.ALGraph;
import model.City;
import model.Edge;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class MAGraphTest {
	
	private MAGraph<Station> m;

	private void stage1() {
		Station s1;
		Station s2;
		Station s3;
		Station s4;
		Station s5;
		m = new MAGraph<Station>();
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
		m = new MAGraph<Station>();
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
	
	private void stage3() {
		m = new MAGraph<Station>();
		Station s0 = new Station("0");
		Station s1 = new Station("1");
		Station s2 = new Station("2");
		Station s3 = new Station("3");
		Station s4 = new Station("4");
		m.add(s0);
		m.add(s1);
		m.add(s2);
		m.add(s3);
		m.add(s4);
		m.connect(s0, s1, 3);
		m.connect(s0, s2, 7);
		m.connect(s1, s3, 5);
		m.connect(s3, s2, 4);
		m.connect(s3, s4, 6);
		m.connect(s2, s4, 8);
	}
	
	private void stage4() {
		m = new MAGraph<Station>();
		Station s1 = new Station("1");
		Station s2 = new Station("2");
		Station s3 = new Station("3");
		Station s4 = new Station("4");
		Station s5 = new Station("5");
		Station s6 = new Station("6");
		m.add(s1);
		m.add(s2);
		m.add(s3);
		m.add(s4);
		m.add(s5);
		m.add(s6);
		m.connect(s1, s2, 3);
		m.connect(s2, s6, 21);
		m.connect(s5, s4, 7);
	}
	

	@Test
	void DFSTest() {
		stage1();
		m.DFS();
		assertTrue(m.getVertexs().get(0).getDistance() == 1 && m.getVertexs().get(0).getF() == 10);
	}
	@Test
	void DFSLTest() {
		stage4();
		m.DFS();
		assertTrue(m.getVertexs().get(0).getDistance() == 1 && m.getVertexs().get(0).getF() == 6);
	}
	@Test
	void BFSTest() {
		stage1();
		m.BFS(m.getVertexs().get(0).getObject());
		assertTrue(m.getVertexs().get(0).getDistance()==0);
	}
	@Test
	void BFSLTest() {
		stage4();
		m.BFS(m.getVertexs().get(0).getObject());
		assertTrue(m.getVertexs().get(2).getDistance()==2147483647);
	}
	@Test
	void floydWarshallTest() {
		stage3();
		int[][] mat = new int[5][5];
		mat[0][0] = 0;
		mat[0][1] = 3;
		mat[0][2] = 7;
		mat[0][3] = 8;
		mat[0][4] = 14;
		mat[1][1] = 0;
		mat[1][0] = 3;
		mat[1][2] = 9;
		mat[1][3] = 5;
		mat[1][4] = 11;
		mat[2][2] = 0;
		mat[2][0] = 7;
		mat[2][1] = 9;
		mat[2][3] = 4;
		mat[2][4] = 8;
		mat[3][3] = 0;
		mat[3][0] = 8;
		mat[3][1] = 5;
		mat[3][2] = 4;
		mat[3][4] = 6;
		mat[4][4] = 0;
		mat[4][0] = 14;
		mat[4][1] = 11;
		mat[4][2] = 8;
		mat[4][3] = 6;
		long[][] matF = m.floydWarshall();
		boolean ce = false;
		for (int i = 0; i < mat.length && !ce; i++) {
			for (int j = 0; j < mat.length && !ce; j++) {
				if(mat[i][j]!=matF[i][j]) {
					ce = true;
				}
			}
		}
		assertTrue(!ce);
	}
	@Test
	void floydWarshallLiTest() {
		stage4();
		int[][] mat = new int[6][6];
		mat[0][0] = 0;
		mat[0][1] = 3;
		mat[0][2] = 2147483647;
		mat[0][3] = 2147483647;
		mat[0][4] = 2147483647;
		mat[0][5] = 24;
		mat[1][1] = 0;
		mat[1][0] = 3;
		mat[1][2] = 2147483647;
		mat[1][3] = 2147483647;
		mat[1][4] = 2147483647;
		mat[1][5] = 21;
		mat[2][2] = 0;
		mat[2][0] = 2147483647;
		mat[2][1] = 2147483647;
		mat[2][3] = 2147483647;
		mat[2][4] = 2147483647;
		mat[2][5] = 2147483647;
		mat[3][3] = 0;
		mat[3][0] = 2147483647;
		mat[3][1] = 2147483647;
		mat[3][2] = 2147483647;
		mat[3][4] = 7;
		mat[3][5] = 2147483647;
		mat[4][4] = 0;
		mat[4][0] = 2147483647;
		mat[4][1] = 2147483647;
		mat[4][2] = 2147483647;
		mat[4][3] = 7;
		mat[4][5] = 2147483647;
		mat[5][5] = 0;
		mat[5][0] = 24;
		mat[5][1] = 21;
		mat[5][2] = 2147483647;
		mat[5][3] = 2147483647;
		mat[5][4] = 2147483647;
		
		long[][] matF = m.floydWarshall();
		boolean ce = false;
		for (int i = 0; i < mat.length && !ce; i++) {
			for (int j = 0; j < mat.length && !ce; j++) {
				if(mat[i][j]!=matF[i][j]) {
					ce = true;
				}
			}
		}
		assertTrue(!ce);
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
	void kruskalLiTest() {
		int camino =0;
		stage4();
		List<Edge<Station>> m1 =  m.Kruskal();
		for (int i = 0; i < m1.size(); i++) {
			camino += m1.get(i).getWeight();
		}
		assertTrue(camino==31);
		
		
	}
	@Test
	void dijkstraTest() {
		stage2();
		m.Dijkstra(m.getVertexs().get(0).getObject());
		int t = m.getVertexs().get(1).getDistance();
		assertTrue(t==4);
	}
	@Test
	void dijkstraLiTest() {
		stage4();
		m.Dijkstra(m.getVertexs().get(0).getObject());
		int t = m.getVertexs().get(1).getDistance();
		assertTrue(t==3);
	}
}
