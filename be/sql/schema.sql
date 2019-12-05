create
database
project;

use
project;

create table user_role
(
  role_id   smallint    not null auto_increment,
  role_name varchar(30) not null,

  primary key (role_id)
);

create table user_account
(
  account_id       bigint       not null auto_increment,
  login            varchar(30)  not null,
  account_password varchar(255) not null,
  role_id          smallint     not null,

  first_name       varchar(35)  not null,
  last_name        varchar(35)  not null,
  birthday         date         not null,

  primary key (account_id),
  constraint fk_user_role_id_role_id foreign key (role_id) references user_role (role_id) on delete cascade on update cascade
);

create table faculty
(
  faculty_id   smallint    not null auto_increment,
  faculty_name varchar(45) not null,

  primary key (faculty_id)
);

create table university_group
(
  group_id   int         not null auto_increment,
  faculty_id smallint    not null,
  speciality varchar(45) not null,
  grade      smallint    not null,
  graduation date        not null,

  primary key (group_id),
  constraint fk_uni_group_faculty_id_faculty_id foreign key (faculty_id) references faculty (faculty_id) on delete cascade on update cascade
);

create table student
(
  student_id int         not null auto_increment,
  account_id bigint      not null,

  group_id   int         not null,
  address    varchar(45) not null,

  primary key (student_id),
  constraint fk_student_account_id_account_id foreign key (account_id) references user_account (account_id) on delete cascade on update cascade,
  constraint fk_student_group_id_group_id foreign key (group_id) references university_group (group_id) on delete cascade on update cascade
);

create table professor
(
  professor_id      int         not null auto_increment,
  account_id        bigint      not null,

  academic_rank     varchar(45) not null,
  field_of_research varchar(45) not null,

  primary key (professor_id),
  constraint fk_professor_account_id_account_id foreign key (account_id) references user_account (account_id) on delete cascade on update cascade
);


create table lesson_time
(
  id         smallint  not null auto_increment,
  begin_time timestamp not null,
  end_time   timestamp not null,

  primary key (id)
);

create table lesson_info
(
  lesson_info_id bigint      not null auto_increment,
  lesson_name    varchar(45) not null,

  primary key (lesson_info_id)
);

create table study_day
(
  day_number smallint    not null auto_increment,
  day_name   varchar(20) not null,

  primary key (day_number)
);

create table lesson
(
  lesson_id      bigint   not null auto_increment,
  lesson_info_id bigint   not null,
  professor_id   int      not null,
  lesson_time_id smallint not null,
  lesson_room    smallint not null,
  study_day      smallint not null,

  primary key (lesson_id),
  constraint groups_schedule_lesson_info_id_lesson_info_id foreign key (lesson_info_id) references lesson_info (lesson_info_id) on delete cascade on update cascade,
  constraint groups_schedule_professor_id foreign key (professor_id) references professor (professor_id) on delete cascade on update cascade,
  constraint groups_schedule_lesson_time_id_id foreign key (lesson_time_id) references lesson_time (id) on delete cascade on update cascade,
  constraint groups_schedule_study_day_day_number foreign key (study_day) references study_day (day_number) on delete cascade on update cascade
);

create table schedule_group
(
  id        bigint not null auto_increment,
  lesson_id bigint not null,
  group_id  int    not null,

  primary key (id),
  constraint schedule_group_group_id_group_id foreign key (group_id) references university_group (group_id) on delete cascade on update cascade,
  constraint schedule_group_lesson_id_lesson_id foreign key (lesson_id) references lesson (lesson_id) on delete cascade on update cascade
);

create table lesson_date
(
  id          bigint NOT NULL AUTO_INCREMENT,
  lesson_id   bigint not null,
  lesson_date DATE   not null,

  primary key (id),
  constraint lesson_date_lesson_id foreign key (lesson_id) references lesson (lesson_id) on delete cascade on update cascade
);

create table attendance
(
  id          bigint   not null auto_increment,
  student_id  int      not null,
  lesson_id   bigint   not null,
  lesson_date date     not null,
  attendance_status      tinyint not null,

  primary key (id),
  constraint attendance_student_id foreign key (student_id) references student (student_id) on delete cascade on update cascade,
  constraint attendance_lesson_id foreign key (lesson_id) references lesson (lesson_id) on delete cascade on update cascade
);