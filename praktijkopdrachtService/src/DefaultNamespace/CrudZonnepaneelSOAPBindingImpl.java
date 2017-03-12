/**
 * CrudZonnepaneelSOAPBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

	package DefaultNamespace;
	
	import java.io.DataInputStream;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.math.BigInteger;
	import java.util.ArrayList;
	import java.util.List;
	
	public class CrudZonnepaneelSOAPBindingImpl implements
			DefaultNamespace.CrudZonnepaneel_PortType {
		public java.lang.String createZP(int serienummer, java.lang.String postcode)
				throws java.rmi.RemoteException {
	
			List<String> list = null;
			String check = null;
	
			try {
				File file = new File("Zonnepanelen.dat");
				if (!file.exists()) {
					String s = serienummer + "-" + postcode;
					
					list = new ArrayList<String>();
					list.add(s);
					saveZpList(list);
					check = "list created";
				} else {
					String s = serienummer + "-" + postcode;
					FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fis);
					list = (List<String>) ois.readObject();
					
					
					for(int i = 0; i < list.size(); i++){
						StringBuilder sb = new StringBuilder();
						sb.append("");
						sb.append(serienummer);
						String strI = sb.toString();
						if(!list.get(i).contains(strI)){
							list.add(s);
							FileOutputStream fos;
							fos = new FileOutputStream(file);
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							oos.writeObject(list);
							oos.close();	
						}
					}
					
					ois.close();
					check = "list already exists";
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return check;
	
		}
	
		@SuppressWarnings("resource")
		public java.lang.String deleteZP(int serienummer, java.lang.String postcode)
				throws java.rmi.RemoteException {
			String check = null;
			DataInputStream input;
			List<String> list = null;
			String s = serienummer + "-" + postcode;
			try {
				input = new DataInputStream(new FileInputStream("Zonnepanelen.dat"));
				while (input.available() > 0) {
					String x = input.toString();
					list.add(x);
				}
	
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).equals(s)) {
						list.remove(i);
					}
				}
	
				try {
					File file = new File("Zonnepanelen.dat");
					if (!file.exists()) {
						String s2 = serienummer + "-" + postcode;
	
						list = new ArrayList<String>();
						list.add(s2);
						saveZpList(list);
						check = "list created";
					} else {
						FileInputStream fis = new FileInputStream(file);
						ObjectInputStream ois = new ObjectInputStream(fis);
						list = (List<String>) ois.readObject();
						ois.close();
						check = "list already exists";
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return check;
		}
	
		public java.lang.String updateZP(java.lang.String serienummer,
				java.lang.String postcode) throws java.rmi.RemoteException {
			String check = null;
			DataInputStream input;
			List<String> list = null;
			String s = serienummer + "-" + postcode;
			
			try {
				input = new DataInputStream(new FileInputStream("Zonnepanelen.dat"));
				while (input.available() > 0) {
					String x = input.toString();
					list.add(x);
				}
	
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).contains(serienummer.toString())) {
						list.remove(i);
						list.add(s);
						
					}
				}
	
				try {
					File file = new File("Zonnepanelen.dat");
					if (!file.exists()) {
						String s2 = serienummer + "-" + postcode;
	
						list = new ArrayList<String>();
						list.add(s2);
						saveZpList(list);
						check = "list created";
					} else {
						FileInputStream fis = new FileInputStream(file);
						ObjectInputStream ois = new ObjectInputStream(fis);
						list = (List<String>) ois.readObject();
						ois.close();
						check = "list already exists";
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return check;
	
		}
		
		private void saveZpList(List<String> zpList) {
			try {
				File file = new File("Zonnepanelen.dat");
				FileOutputStream fos;
				fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(zpList);
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
