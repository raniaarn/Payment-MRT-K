package id.ac.ui.cs.advprog.b5.payment.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserWalletCommandTest {
    @Test
    void testWalletCommandProperties() {
        UserWalletCommand command = new UserWalletCommand();

        Long expectedId = 110L;
        command.setId(expectedId);

        Integer expectedUserId = 110;
        command.setUserId(expectedUserId);

        String expectedCommand = "TOPUP 100";
        command.setCommandName(expectedCommand);

        Long actualId = command.getId();
        Integer actualUserId = command.getUserId();
        String actualName = command.getCommandName();

        Assertions.assertEquals(expectedId, actualId);
        Assertions.assertEquals(expectedUserId, actualUserId);
        Assertions.assertEquals(expectedCommand, actualName);
    }


    @Test
    void testToString() {
        UserWalletCommand command = new UserWalletCommand(110L, 110, "TOPUP 100");

        String expectedToString = "UserWalletCommand(id=110, userId=110, commandName=TOPUP 100)";
        String actualToString = command.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }

    @Test
    void testHashCode() {
        UserWalletCommand command = new UserWalletCommand(110L, 110, "TOPUP 100");
        UserWalletCommand command2 = new UserWalletCommand(110L, 110, "TOPUP 100");
        UserWalletCommand command3 = new UserWalletCommand(20L, 130, "TOPUP 102");

        Assertions.assertEquals(command.hashCode(), command.hashCode());
        Assertions.assertNotEquals(command.hashCode(), command3.hashCode());
        Assertions.assertEquals(command.hashCode(), command2.hashCode());
        Assertions.assertEquals(command.hashCode(), command.hashCode());

        int initialHashCode = command.hashCode();
        command.setCommandName("PAY 2000");
        Assertions.assertNotEquals(command.hashCode(), initialHashCode);
        Assertions.assertNotEquals(command.hashCode(), (Integer) null);

        UserWalletCommand command4 = new UserWalletCommand(110L, 110, null);
        UserWalletCommand command5 = new UserWalletCommand(110L, 110, null);

        Assertions.assertEquals(command4.hashCode(), command5.hashCode());
        Assertions.assertNotEquals(command.hashCode(), command5.hashCode());
    }

    @Test
    void testEquals() {
        UserWalletCommand command = new UserWalletCommand(110L, 110, "TOPUP 100");
        UserWalletCommand command2 = new UserWalletCommand(110L, 110, "TOPUP 100");
        UserWalletCommand command3 = new UserWalletCommand(20L, 130, "TOPUP 102");
        Wallet wallet = new Wallet(110, 200, 100);
        UserWalletCommand command4 = command;

        Assertions.assertEquals(true, command.equals(command));
        Assertions.assertEquals(true, command.equals(command4));
        Assertions.assertEquals(true, command.equals(command2));
        Assertions.assertNotEquals(true, command.equals(command3));
        Assertions.assertNotEquals(true, command.equals("someString"));
        Assertions.assertNotEquals(true, command.equals(null));
        Assertions.assertNotEquals(true, command.equals(wallet));

        UserWalletCommand command5 = new UserWalletCommand(110L, 110, null);
        UserWalletCommand command6 = new UserWalletCommand(110L, 110, null);

        Assertions.assertEquals(command5.hashCode(), command6.hashCode());
        Assertions.assertNotEquals(command.hashCode(), command6.hashCode());
    }

    @Test
    void testToStringBuilder() {
        UserWalletCommand.UserWalletCommandBuilder builder = new UserWalletCommand.UserWalletCommandBuilder();
        builder.userId(110);
        builder.id(110L);
        builder.commandName("TOPUP 100");

        String expectedToString = "UserWalletCommand.UserWalletCommandBuilder(id=110, userId=110, commandName=TOPUP 100)";
        String actualToString = builder.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }

}
