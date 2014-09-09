package com.tcbuen.pqrs.exceptions;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * 
 */
public class ZMessManager extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public final static String ALL = "All ";
	public final static String ENTCHILD = "related tables(childs)";
	public final static String FOREIGNDATA = "foreign classes data: ";
	public static String ENTITY_SUCCESFULLYSAVED = "Entidad guardada satisfactoriamente";
	public static String ENTITY_SUCCESFULLYDELETED = "Entity succesfully deleted";
	public static String ENTITY_SUCCESFULLYMODIFIED = "Entidad modificada satisfactoriamente";
	public static String ENTITY_WITHSAMEKEY = "Another Entity with the same key was found";
	public static String ENTITY_NOENTITYTOUPDATE = "No Entity was found, with the typed key ";

	public ZMessManager() {
	}

	public ZMessManager(String exception) {
		super(exception);
	}

	public class NotValidFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFieldException(String info) {
			super("El campo: \"" + info + "\" No es valido");
		}
	}
	
	public class NullEntityExcepcion extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NullEntityExcepcion(String info) {
			super("La entidad " + info + " no puede ser nulo o vacio");
		}
	}

	public class EmptyFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public EmptyFieldException(String info) {
			super("El campo: \"" + info
					+ "\" no puede estar vacio");
		}
	}

	public class NotValidFormatException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFormatException(String info) {
			super("El fotmsto o el largo de lo escrito en el campo: \"" + info
					+ "\" no es valido");
		}
	}

	public class DeletingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public DeletingException(String info) {
			super("The Entity you are trying to delete "
					+ "may have related information, "
					+ "please before trying again, "
					+ "check the data on the entity, \"" + info+"\"");
		}
	}
	
	public class ForeignException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public ForeignException(String info) {
			super("No existen datos relacionados con: \"" + info+ "\"");
		}
	}	

	public class GettingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public GettingException(String info) {
			super("Existe una excepcion tratando de obtener " + info);
		}
	}

	public class FindingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public FindingException(String info) {
			super("Hay una excepción tratando de encontrar:  " + info);
		}
	}

}
