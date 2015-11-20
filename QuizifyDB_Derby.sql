DROP TABLE submission_answer;
DROP TABLE quiz_submission;
DROP TABLE quiz_question;
DROP TABLE quiz;
DROP TABLE answer;
DROP TABLE question;
DROP TABLE account;

CREATE TABLE account
(
  id                 INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username           VARCHAR(30) NOT NULL,
  password           VARCHAR(30) NOT NULL,
  email              VARCHAR(30),
  registration_date  DATE,
  PRIMARY KEY (id), 
  UNIQUE (username)
);


CREATE TABLE question
(
  id        INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  text      VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE answer
(
  id              INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  question_id     INTEGER,
  text            VARCHAR(50) NOT NULL,
  isCorrect       CHAR(1),
  PRIMARY KEY (id),
  FOREIGN KEY (question_id) REFERENCES question(id)
);

CREATE TABLE quiz
(
  id     INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name        VARCHAR(15),
  PRIMARY KEY (id)
);


CREATE TABLE quiz_question
(
  quiz_id        INTEGER NOT NULL,
  question_id    INTEGER NOT NULL,
  PRIMARY KEY (quiz_id, question_id),
  FOREIGN KEY (quiz_id) REFERENCES quiz(id),
  FOREIGN KEY (question_id) REFERENCES question(id)
);


CREATE TABLE quiz_submission
(
  id                 INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  user_id            INTEGER NOT NULL,  
  quiz_id            INTEGER NOT NULL,
  submission_time    TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES account(id),
  FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);


CREATE TABLE submission_answer
(
  id                  INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  submission_id       INTEGER NOT NULL,
  question_id         INTEGER NOT NULL,  
  answer_id           INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (submission_id) REFERENCES quiz_submission(id),
  FOREIGN KEY (question_id) REFERENCES question(id),
  FOREIGN KEY (answer_id) REFERENCES answer(id)  
);

INSERT INTO answer (text, isCorrect)
            VALUES ('--Skipped--', 'N');

INSERT INTO account (username, password, email, registration_date)
            VALUES ('joe', 'joe', 'joe@example.com', '2015-01-07');
INSERT INTO account (username, password, email, registration_date)
            VALUES ('jim', 'jim', 'jim@example.com', '2015-01-08');
INSERT INTO account (username, password, email, registration_date)
            VALUES ('jill', 'jill', 'jill@example.com', '2015-01-09');
            
INSERT INTO question (text)
            VALUES ('What is the capital of Alabama?');
INSERT INTO question (text)
            VALUES ('What is the capital of Alaska?');
INSERT INTO question (text)
            VALUES ('What is the capital of Arizona?');
INSERT INTO question (text)
            VALUES ('What is the capital of Arkansas?');

INSERT INTO answer (question_id, text, isCorrect)
            VALUES (1, 'Birmingham', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (1, 'Montgomery', 'Y');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (1, 'Mobile', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (1, 'Huntsville', 'N');
            
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (2, 'Anchorage', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (2, 'Fairbanks', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (2, 'Juneau', 'Y');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (2, 'Sitka', 'N');

INSERT INTO answer (question_id, text, isCorrect)
            VALUES (3, 'Phoenix', 'Y');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (3, 'Tucson', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (3, 'Tempe', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (3, 'Flagstaff', 'N');

INSERT INTO answer (question_id, text, isCorrect)
            VALUES (4, 'Little Rock', 'Y');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (4, 'Fort Smith', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (4, 'Fayetteville', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (4, 'Springdale', 'N');

INSERT INTO quiz (name)
            VALUES ('State Capitals');


INSERT INTO quiz_question (quiz_id, question_id)
            VALUES (1, 1);
INSERT INTO quiz_question (quiz_id, question_id)
            VALUES (1, 2);
INSERT INTO quiz_question (quiz_id, question_id)
            VALUES (1, 3);
INSERT INTO quiz_question (quiz_id, question_id)
            VALUES (1, 4);
































INSERT INTO question (text)
            VALUES ('What does IPA stand for?');
INSERT INTO question (text)
            VALUES ('Which is not an ingredient in beer?');
INSERT INTO question (text)
            VALUES ('How many types of beer are there?');
INSERT INTO question (text)
            VALUES ('In what year were the first beer cans produced?');

INSERT INTO answer (question_id, text, isCorrect)
            VALUES (5, 'Imperial Pale Ale', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (5, 'Imported Premium Ale', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (5, 'Indian Pale Ale', 'Y');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (5, 'Irish Porter Ale', 'N');
            
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (6, 'Salt', 'Y');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (6, 'Hops', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (6, 'Yeast', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (6, 'Water', 'N');

INSERT INTO answer (question_id, text, isCorrect)
            VALUES (7, 'About 300', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (7, 'About 400', 'Y');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (7, 'About 500', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (7, 'About 600', 'N');

INSERT INTO answer (question_id, text, isCorrect)
            VALUES (8, '1950', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (8, '1900', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (8, '1965', 'N');
INSERT INTO answer (question_id, text, isCorrect)
            VALUES (8, '1935', 'Y');

INSERT INTO quiz (name)
            VALUES ('Beer Trivia');


INSERT INTO quiz_question (quiz_id, question_id)
            VALUES (2, 5);
INSERT INTO quiz_question (quiz_id, question_id)
            VALUES (2, 6);
INSERT INTO quiz_question (quiz_id, question_id)
            VALUES (2, 7);
INSERT INTO quiz_question (quiz_id, question_id)
            VALUES (2, 8);































            
INSERT INTO quiz_submission (user_id, quiz_id, submission_time)
            VALUES (1, 1, '2015-03-01 11:03:20');
INSERT INTO quiz_submission (user_id, quiz_id, submission_time)
            VALUES (2, 1, '2015-03-01 12:03:20');
INSERT INTO quiz_submission (user_id, quiz_id, submission_time)
            VALUES (3, 1, '2015-03-01 13:03:20');

INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (1, 1, 1);
INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (1, 2, 4);
INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (1, 3, 1);
INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (1, 4, 3);

INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (2, 1, 1);
INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (2, 2, 4);
INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (2, 3, 2);
INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (2, 4, 3);

INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (3, 1, 4);
INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (3, 2, 3);
INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (3, 3, 2);
INSERT INTO submission_answer (submission_id, question_id, answer_id)
            VALUES (3, 4, 1);