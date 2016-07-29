package com.imooc.collection;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		if(!o1.id.equals(o2.id))
			return o1.id.compareTo(o2.id);
		return o1.name.compareTo(o2.name);

	}

}
