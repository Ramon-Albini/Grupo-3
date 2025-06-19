# Documentação API

## Visão geral:  
 
   A API faz o gerenciamento de sistemas acadêmicos que permite o cadastro, edição e exclusão de informações como alunos (Students), Usuários(Users), Cursos(Courses), Notas(Grades), Faltas(Absences)
   O sistema utiliza arquitetura RESTful com SpringBoot e DTOs para transporte de dados,separação de camadas e uso de anotações JPA.Todas as repostas estão no padrão .json e caso haja possíveis erros a api retorna códigos de erro apropriados

   ## Endpoints e Métodos HTTP:

 ## Alunos (Students):

- GET/students - Lista todos os alunos

- GET/students/{id} - Retorna um aluno pelo ID

- POST/students - Cria um novo aluno

- PUT/students/{id} - Atualiza um aluno existente

- DELETE /students/{id} - Remove um aluno

## Usuários (Users)

- GET/users - Lista todos os usuários

- GET/users/{id} - Retorna um usuário pelo ID

- POST/users - Cria um novo usuário

- PUT/users/{id} - Atualiza um usuário existente

- DELETE/users/{id} - Remove um usuário 

## Matérias (Courses)

- GET/courses - Lista todos as matérias

- GET/courses/{id} - Retorna uma matéria pelo ID

- POST/courses - Cria uma nova matéria

- PUT/courses/{id} - Atualiza uma matéria existente

- DELETE/courses/{id} - Remove uma matéria

## Notas (Grades)

- GET/grades - Lista todas as aulas

- GET/grades/{id} - Retorna notas pelo ID

- POST/grades - Cria um nova nota

- PUT/grades/{id} - Atualiza uma nota existente

- DELETE /grades/{id} - Remove uma nota

 ## Faltas (Absences)

- GET/absences - Lista todos as faltas

- GET/absences/{id} - Retorna faltas pelo ID

- POST/absences - Cria um nova falta

- PUT/absences/{id} - Atualiza uma falta existente

- DELETE/absences/{id} - Remove uma falta
Path Parameters

- {id}: Identificador único do recurso (Long).

## Body Parameters (exemplo: StudentDTO):

```json
{
  "name": "João Silva",
  "coursesIds": [1, 2],
  "gradesIds": [10, 11],
  "absencesIds": [5],
  "userId": 3
}
```

## Exemplos de Requisição

Criar novo aluno (POST /students)

POST/students
Content-Type: application/json

``` json
{
  "name": "Maria Oliveira",
  "coursesIds": [1],
  "gradesIds": [],
  "absencesIds": [],
  "userId": 2
}
```

Obter nota por ID (GET/grades/4):
   GET/grades/4

Atualizar curso (PUT /courses/2):

PUT /courses/2
Content-Type: application/json

```json
{
  "name": "Matemática Avançada",
  "studentsIds": [1, 2, 3]
}
```

 # Respostas

 Sucesso

- 200 OK - Requisição processada 

- 201 Created - Recurso criado 

- 204 No Content - Recurso deletado 

Erros

- 404 Not Found - Não encontrado

- 400 Bad Request - Dados inválidos

- 500 Internal Server Error - Erro interno

 Mensagens de Erro (padrão)

```json 
{
  "timestamp": "2025-06-04T14:35:22.123+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Student not found",
  "path": "/students/99"
} 
 ```

## Organização do projeto

**controllers** - Exposição da API REST com endpoints para entidades: Student, User, Course, Grade e Absence.

**services** - Camada de lógica de negócio. Valida dados, relaciona entidades e converte DTOs para entidades persistentes.

**repositories** - Interfaces JPA para persistência e busca no banco de dados.

**models** - entidades JPA com mapeamento relacional (@entity, @ManyToOne)

**dtos** - objetos de transferências de dados 

**exceptions** - Exceções para melhorar clareza e consistência no tratamento e mensagens de erros.


## Documentação das Models e Exception:

**Tabela Student:**
  - campos: id (Long), name(String), courses(List<Course>), grades(List<grade>), absenses(List<Absence>), user(User 1:1)

**Tabela Course:**
- campos: id(Long), name(String), students(List<Students>)  

**Tabela Grades:**
- campos: id(Long), value(double), date(Date), description(String), course(Course), student(student)

**Tabela Absences:**
- campos: id (Long), date (Date), reason(String), course (Course), student(Student)

**Tabela Users:**
- campos: id(Long), login(String), password(String),student(Student)

## Relacionamento entre entidades:

 **Student**  1:N  **grade,absence** </br>
 **Student**  N:M  **Courses** </br>
 **User**  1:1  **Student** </br>
 **Grade**  N:1  **Student, Course** </br>
 **Absense**  N:1  **Student, Course** </br>
