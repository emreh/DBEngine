package owl.my.firend.controler.ejb.impl.pub;

import java.util.concurrent.TimeUnit;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

import owl.my.firend.controler.ejb.interfaces.pub.AllStateRemote;

/**
 * Session Bean implementation class AllState
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)
public class AllState implements AllStateRemote {

	public AllState() {

	}
}