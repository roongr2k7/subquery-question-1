package todo

class TaskList {

  String listName

  static hasMany = ['tasks': Task]

    static constraints = {
    }
}
