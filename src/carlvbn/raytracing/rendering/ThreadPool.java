package carlvbn.raytracing.rendering;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class ThreadPool {
	private ArrayBlockingQueue<Runnable> queu;
	private List<Thread> threads = new ArrayList<>();

	private class PoolWorker implements Runnable {
		public void run() {

			try {
				while (!Thread.interrupted()) {
					Runnable r = queu.take();
					r.run();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ThreadPool(int sizeq, int N) {
		queu = new ArrayBlockingQueue<Runnable>(sizeq);
		for (int i = 0; i < N; i++) {
			Thread t = new Thread(new PoolWorker());
			t.start();
			threads.add(t);
		}
		
	}

	public void submit(Runnable r) throws InterruptedException {
		queu.put(r);

	}

}
