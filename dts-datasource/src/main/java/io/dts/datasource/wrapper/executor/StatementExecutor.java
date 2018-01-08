

package io.dts.datasource.wrapper.executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class StatementExecutor extends AbstractExecutor {


  private final StatementModel statementUnit;


  public StatementExecutor(StatementModel statementUnit) {
    super();
    this.statementUnit = statementUnit;
  }

  public ResultSet executeQuery() throws Exception {
    return super.executeStatement(statementUnit, new ExecuteCallback<ResultSet>() {

      @Override
      public ResultSet execute(final StatementModel statModel) throws Exception {
        return statModel.getStatement().getRawStatement().executeQuery(statModel.getSql());
      }
    });
  }


  public int executeUpdate() throws Exception {
    return executeUpdate(new Updater() {

      @Override
      public int executeUpdate(final Statement statement, final String sql) throws SQLException {
        return statement.executeUpdate(sql);
      }
    });
  }


  public int executeUpdate(final int autoGeneratedKeys) throws Exception {
    return executeUpdate(new Updater() {

      @Override
      public int executeUpdate(final Statement statement, final String sql) throws SQLException {
        return statement.executeUpdate(sql, autoGeneratedKeys);
      }
    });
  }


  public int executeUpdate(final int[] columnIndexes) throws Exception {
    return executeUpdate(new Updater() {

      @Override
      public int executeUpdate(final Statement statement, final String sql) throws SQLException {
        return statement.executeUpdate(sql, columnIndexes);
      }
    });
  }


  public int executeUpdate(final String[] columnNames) throws Exception {
    return executeUpdate(new Updater() {

      @Override
      public int executeUpdate(final Statement statement, final String sql) throws SQLException {
        return statement.executeUpdate(sql, columnNames);
      }
    });
  }

  private int executeUpdate(final Updater updater) throws Exception {
    Integer results = super.executeStatement(statementUnit, new ExecuteCallback<Integer>() {

      @Override
      public Integer execute(final StatementModel statModel) throws Exception {

        return updater.executeUpdate(statModel.getStatement().getRawStatement(),
            statModel.getSql());
      }
    });
    return results;
  }


  public boolean execute() throws Exception {
    return execute(new Executor() {

      @Override
      public boolean execute(final Statement statement, final String sql) throws SQLException {
        return statement.execute(sql);
      }
    });
  }


  public boolean execute(final int autoGeneratedKeys) throws Exception {
    return execute(new Executor() {

      @Override
      public boolean execute(final Statement statement, final String sql) throws SQLException {
        return statement.execute(sql, autoGeneratedKeys);
      }
    });
  }


  public boolean execute(final int[] columnIndexes) throws Exception {
    return execute(new Executor() {

      @Override
      public boolean execute(final Statement statement, final String sql) throws SQLException {
        return statement.execute(sql, columnIndexes);
      }
    });
  }

  public boolean execute(final String[] columnNames) throws Exception {
    return execute(new Executor() {

      @Override
      public boolean execute(final Statement statement, final String sql) throws SQLException {
        return statement.execute(sql, columnNames);
      }
    });
  }

  private boolean execute(final Executor executor) throws Exception {
    Boolean result = super.executeStatement(statementUnit, new ExecuteCallback<Boolean>() {

      @Override
      public Boolean execute(final StatementModel statModel) throws Exception {
        return executor.execute(statModel.getStatement().getRawStatement(), statModel.getSql());
      }
    });
    if (null == result) {
      return false;
    }
    return result;
  }

  private interface Updater {

    int executeUpdate(Statement statement, String sql) throws SQLException;
  }

  private interface Executor {

    boolean execute(Statement statement, String sql) throws SQLException;
  }
}
