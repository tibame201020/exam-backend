# Exam System API Documentation

This document describes the backend endpoints for the Exam System. All endpoints are prefixed with `/api`.

- **Base URL**: `http://localhost:8080/api` (default)
- **Content-Type**: `application/json`

---

## 1. Exam Management (QuizController)

### Get Exam List
- **Endpoint**: `/getExamList`
- **Method**: `GET` / `POST`
- **Description**: Returns a list of all available exam names.
- **Response**: `string[]` (Array of exam names)

### Search Exam List
- **Endpoint**: `/getExamListByKeyWord`
- **Method**: `POST`
- **Request Body**: `string` (The keyword to filter exam names)
- **Response**: `string[]` (Filtered array of exam names)

### Check Exam Name Existence
- **Endpoint**: `/checkExamNm`
- **Method**: `POST`
- **Request Body**: `string` (The name of the exam to check)
- **Response**: `boolean` (True if exists, false otherwise)

### Add/Update Exam
- **Endpoint**: `/addExam`
- **Method**: `POST`
- **Request Body**: `Exam` (Full exam object with quizzes)
- **Response**: `boolean` (True if saved successfully)

### Remove Exam
- **Endpoint**: `/removeExam`
- **Method**: `POST`
- **Request Body**: `string` (The name of the exam to delete)
- **Response**: `boolean` (True if deleted successfully)

### Get Full Exam Details
- **Endpoint**: `/getExamByName`
- **Method**: `POST`
- **Request Body**: `string` (The name of the exam)
- **Response**: `Exam` (The exam object including all quizzes)

---

## 2. Exam Mode (ExamModeController)

### Get Random Quizzes for Exam
- **Endpoint**: `/getExamModeQuizzes`
- **Method**: `POST`
- **Description**: Fetches a randomized set of quizzes and generates a record.
- **Request Body**: `ExamModeParam`
- **Response**: `ExamRecord`

### Submit Answers
- **Endpoint**: `/commitAnsToRecord`
- **Method**: `POST`
- **Description**: Submits the user's answers and returns the updated record with score info.
- **Request Body**: `ExamRecord`
- **Response**: `ExamRecord`

### Get Score by ID
- **Endpoint**: `/getScoreById`
- **Method**: `POST`
- **Request Body**: `number` (long - The record ID)
- **Response**: `ExamRecordScore`

### Search Score History
- **Endpoint**: `/getScoreByKeyword`
- **Method**: `POST`
- **Request Body**: `string` (Keyword to filter history)
- **Response**: `ExamRecordScore[]`

### Delete Record Score
- **Endpoint**: `/deleteRecordScore`
- **Method**: `POST`
- **Request Body**: `number` (long - The record ID)
- **Response**: `boolean`

### Get Detailed Record by ID
- **Endpoint**: `/getExamRecordById`
- **Method**: `POST`
- **Request Body**: `number` (long - The record ID)
- **Response**: `ExamRecord`

---

## 3. Data Models (DTOs)

### `Exam`
```typescript
{
  name: string;        // Unique identifier (ID)
  quizzes: Quiz[];     // Array of quiz objects
}
```

### `Quiz`
```typescript
{
  quizContent: string;        // The question text
  chooses: string[];          // Multiple choice options
  correctContents: string[];  // Correct answers (content match)
  solution: string;           // Explanation or solution details
}
```

### `ExamModeParam`
```typescript
{
  name: string;      // Name of the exam to take
  quizzesNum: number; // Number of random quizzes to pick (0 for all)
}
```

### `ExamRecord`
```typescript
{
  id: number;           // Record ID (Auto-generated on creation)
  examName: string;     // Name of the exam
  examQuizzes: Quiz[];  // The quizzes presented to the user
  ansQuizzes: Quiz[];   // The user's answers (quizzes with user selections)
  logTime: string;      // ISO Timestamp
}
```

### `ExamRecordScore`
```typescript
{
  id: number;
  examName: string;
  correctNums: number;  // Number of correct answers
  quizNums: number;     // Total number of quizzes
  score: string;        // Calculated score (e.g., "85%")
  logTime: string;      // Timestamp
}
```
